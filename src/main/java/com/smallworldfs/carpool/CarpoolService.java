package com.smallworldfs.carpool;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CarpoolService {

    private final JourneyRepository journeyRepository = new JourneyRepository();
    private final JourneyHandler journeyHandler = new JourneyHandler();
    private final ReadWriteLock setupLock = new ReentrantReadWriteLock();

    public void registerJourney(Journey journey) {
        setupLock.readLock().lock();

        try {
            journeyRepository.saveJourney(journey);
            journeyHandler.enqueue(journey);
        } finally {
            setupLock.readLock().unlock();
        }
    }

    public Journey getJourney(Integer journeyId) {
        return journeyRepository.getJourney(journeyId);
    }

    public void setCars(List<Car> cars) {
        setupLock.writeLock().lock();

        try {
            journeyRepository.deleteAll();
            journeyHandler.setCars(cars);
        } finally {
            setupLock.writeLock().unlock();
        }
    }

    public void dropOff(Integer journeyId) {
        setupLock.readLock().lock();

        try {
            journeyHandler.dropOff(getOngoingJourney(journeyId));
        } finally {
            setupLock.readLock().unlock();
        }
    }

    private Journey getOngoingJourney(Integer journeyId) {
        return Optional.of(journeyRepository.getJourney(journeyId))
                .filter(Journey::hasAssignedCar)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Journey " + journeyId + " cannot be ended because it never started!"));
    }
}
