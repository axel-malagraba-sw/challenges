package com.smallworldfs.carpool;

import java.util.List;

public class CarpoolService {

    private final JourneyRepository journeyRepository = new JourneyRepository();
    private final JourneyHandler journeyHandler = new JourneyHandler();

    public void registerJourney(Journey journey) {
        journeyRepository.saveJourney(journey);
        journeyHandler.enqueue(journey);
    }

    public synchronized void setCars(List<Car> cars) {
        journeyRepository.deleteAll();
        journeyHandler.setCars(cars);
    }

    public void dropOff(Integer journeyId) {
        Journey journey = journeyRepository.getJourney(journeyId);

        if (journey.isPendingAssignment()) {
            throw new IllegalArgumentException("Journey " + journey + " cannot be ended because it never started!");
        }
        journeyHandler.dropOff(journey);
    }
}
