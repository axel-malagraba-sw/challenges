package com.smallworldfs.carpool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Journey {

    private final Integer journeyId;
    private final Integer passengers;
    private Car car;
    private boolean completed;

    public boolean isPendingAssignment() {
        return car == null;
    }

    public void complete() {
        completed = true;
    }

    public void assign(Car car) {
        this.car = car;
    }
}
