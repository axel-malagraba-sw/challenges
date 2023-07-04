package com.smallworldfs.carpool;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Car {

    private final Integer carId;
    private final Integer maxCapacity;
    private Integer capacity;

    public Car(Integer carId, Integer maxCapacity) {
        this.carId = carId;
        this.maxCapacity = maxCapacity;
        this.capacity = maxCapacity;
    }

    public void increaseCapacity(int amount) {
        changeCapacity(amount);
    }

    public void decreaseCapacity(int amount) {
        changeCapacity(-amount);
    }

    private synchronized void changeCapacity(int amount) {
        capacity += amount;
    }

    public boolean hasCapacity(int minCapacity) {
        return capacity >= minCapacity;
    }
}
