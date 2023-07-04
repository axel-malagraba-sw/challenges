package com.smallworldfs.carpool;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class JourneyRepository {

    private final Map<Integer, Journey> journeys = new ConcurrentHashMap<>();

    public Journey getJourney(Integer journeyId) {
        return Optional.ofNullable(journeys.get(journeyId))
                .orElseThrow(() -> new IllegalArgumentException("Journey " + journeyId + " does not exist"));
    }

    public void saveJourney(Journey journey) {
        journeys.put(journey.getJourneyId(), journey);
    }

    public void deleteAll() {
        journeys.clear();
    }
}
