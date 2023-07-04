package com.smallworldfs.carpool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CarIndex {

    private final Map<Integer, LinkedList<Car>> carsByMaxCapacity = new HashMap<>();
    private int maxCapacity;
    private int minCapacity;

    public void indexCars(List<Car> cars) {
        carsByMaxCapacity.clear();
        cars.forEach(this::indexCar);
    }

    private void indexCar(Car car) {
        carsByMaxCapacity.computeIfAbsent(car.getMaxCapacity(), k -> new LinkedList<>()).add(car);
        maxCapacity = Math.max(maxCapacity, car.getMaxCapacity());
        minCapacity = Math.min(minCapacity, car.getMaxCapacity());
    }

    public Optional<Car> findCar(int capacity) {
        for (int i = Math.max(capacity, minCapacity); i <= maxCapacity; i++) {
            Optional<Car> car = selectCar(capacity, carsByMaxCapacity.get(i));

            if (car.isPresent()) {
                return car;
            }
        }
        return Optional.empty();
    }

    private Optional<Car> selectCar(int minCapacity, LinkedList<Car> cars) {
        if (cars == null) {
            return Optional.empty();
        }
        return cars.stream().filter(car -> car.hasCapacity(minCapacity)).findFirst();
    }
}
