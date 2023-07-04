package com.smallworldfs.carpool;

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

    @BeforeEach
    void setUp() {
        service.setCars(createCars());
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

        assertTrue(journeys.stream().allMatch(Journey::isCompleted));
    }

    private void scheduleJourneysAndWait(List<Journey> journeys) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(journeys.size());

        for (Journey journey : journeys) {
            executorService.submit(() -> {
                registerJourney(journey);
                dropOffJourney(journey);
                count.countDown();
            });
        }
        count.await();
    }

    private void registerJourney(Journey journey) {
        sleepRandom();
        service.registerJourney(journey);
    }

    private void dropOffJourney(Journey journey) {
        do {
            sleepRandom();
        } while (journey.isPendingAssignment());

        service.dropOff(journey.getJourneyId());
    }

    @SneakyThrows
    private void sleepRandom() {
        Thread.sleep(random.nextLong(1000));
    }

    private List<Journey> createJourneys(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(id -> new Journey(id, random.nextInt(6) + 1))
                .toList();
    }
}
