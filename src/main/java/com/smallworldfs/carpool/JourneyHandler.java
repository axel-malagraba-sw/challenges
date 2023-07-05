package com.smallworldfs.carpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public class JourneyHandler {

    private Thread worker;

    private final CarIndex carIndex = new CarIndex();
    private final Queue<Journey> newJourneys = new ConcurrentLinkedQueue<>();
    private final List<Journey> awaitingJourneys = new ArrayList<>();
    private final AtomicBoolean pendingActivity = new AtomicBoolean();

    @SneakyThrows
    public void setCars(List<Car> cars) {
        stopWorkerThread();
        clearJourneys();
        indexCars(cars);
        startWorkerThread();
    }

    private void stopWorkerThread() throws InterruptedException {
        if (worker != null) {
            worker.interrupt();
            worker.join();
        }
    }

    private void clearJourneys() {
        newJourneys.clear();
        awaitingJourneys.clear();
    }

    private void indexCars(List<Car> cars) {
        carIndex.indexCars(cars);
    }

    private void startWorkerThread() {
        worker = new Thread(this::assignJourneys, "Journey-Handler");
        worker.start();
        System.out.println("Journey handler worker started");
    }

    public void enqueue(Journey journey) {
        newJourneys.add(journey);
        signalActivity();
    }

    private synchronized void signalActivity() {
        pendingActivity.set(true);
        notify();
    }

    public void dropOff(Journey journey) {
        journey.complete();
        logDropOff(journey);
        signalActivity();
    }

    private void logDropOff(Journey journey) {
        System.out.println("Dropped off " + journey.getPassengers() + " passengers");
    }

    private void assignJourneys() {
        try {
            while (true) {
                pollQueue();
                assignAwaitingJourneys();
                awaitActivity();
            }
        } catch (InterruptedException e) {
            System.out.println("Journey handler worker stopped");
        }
    }

    private synchronized void awaitActivity() throws InterruptedException {
        while (!pendingActivity.get()) {
            wait();
        }
        pendingActivity.set(false);
    }

    private void pollQueue() {
        Journey journey;

        while ((journey = newJourneys.poll()) != null) {
            awaitingJourneys.add(journey);
        }
    }

    private void assignAwaitingJourneys() {
        awaitingJourneys.removeIf(this::assignJourney);
    }

    private boolean assignJourney(Journey journey) {
        checkInterruption();
        Optional<Car> assignedCar = carIndex.findCar(journey.getPassengers());

        assignedCar.ifPresent(car -> {
            car.decreaseCapacity(journey.getPassengers());
            journey.assign(car);
            logAssignment(journey, car);
        });
        return assignedCar.isPresent();
    }

    private void logAssignment(Journey journey, Car car) {
        System.out.println("Assigned car " + car.getCarId()
                + " to journey " + journey.getJourneyId()
                + " with " + journey.getPassengers() + " passengers");
    }

    @SneakyThrows
    private void checkInterruption() {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }
}
