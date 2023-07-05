package com.smallworldfs.carpool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarpoolServiceTest {

    private static final int CONCURRENCY = 20;

    private final Random random = new Random();
    private final CarpoolService service = new CarpoolService();
    private final ExecutorService executorService = Executors.newFixedThreadPool(CONCURRENCY);

    private List<Car> cars;

    @BeforeEach
    void reset() {
        cars = createCars();
        service.setCars(cars);
    }

    private List<Car> createCars() {
        return List.of(
                new Car(1, 4),
                new Car(2, 5),
                new Car(6, 4),
                new Car(7, 6));
    }

    @Test
    void handles_concurrent_journeys() throws InterruptedException {
        List<Journey> journeys = createJourneys(100);

        scheduleJourneysAndWait(journeys);

        assertThatCarCapacityIsConsistent();
        assertThatAllJourneysAreCompleted(journeys);
    }

    @Test
    void handles_concurrent_reset() throws InterruptedException {
        List<Journey> journeys = createJourneys(200);

        scheduleJourneysWithResetAtAndWait(journeys, 100);

        assertThatCarCapacityIsConsistent();
        assertThatAllJourneysAfterIdAreCompleted(journeys, 100);
    }

    private List<Journey> createJourneys(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(id -> new Journey(id, random.nextInt(6) + 1))
                .toList();
    }

    private void scheduleJourneysAndWait(List<Journey> journeys) throws InterruptedException {
        scheduleJourneysWithResetAtAndWait(journeys, -1);
    }

    private void scheduleJourneysWithResetAtAndWait(List<Journey> journeys, int resetAt) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(journeys.size());

        for (Journey journey : journeys) {
            executorService.submit(() -> executeJourney(resetAt, count, journey));
        }
        count.await();
    }

    private void executeJourney(int resetAt, CountDownLatch count, Journey journey) {
        if (resetAt == journey.getJourneyId()) {
            reset();
        }
        try {
            registerJourney(journey);
            dropOffJourney(journey);
        } finally {
            count.countDown();
        }
    }

    private void registerJourney(Journey journey) {
        sleepRandom();
        service.registerJourney(journey);
    }

    private void dropOffJourney(Journey journey) {
        waitForJourneyToBeAssignedCarWhileItExists(journey);
        service.dropOff(journey.getJourneyId());
    }

    private void waitForJourneyToBeAssignedCarWhileItExists(Journey journey) {
        do {
            service.getJourney(journey.getJourneyId());
            sleepRandom();
        } while (!journey.hasAssignedCar());
    }

    private void assertThatAllJourneysAreCompleted(List<Journey> journeys) {
        assertThatAllJourneysAfterIdAreCompleted(journeys, -1);
    }

    private void assertThatAllJourneysAfterIdAreCompleted(List<Journey> journeys, int journeyId) {
        assertTrue(journeys.stream()
                .filter(journey -> journeyId == journey.getJourneyId())
                .allMatch(Journey::isCompleted));
    }

    private void assertThatCarCapacityIsConsistent() {
        assertEquals(createCars(), cars);
    }

    @SneakyThrows
    private void sleepRandom() {
        Thread.sleep(random.nextLong(1000));
    }
}
