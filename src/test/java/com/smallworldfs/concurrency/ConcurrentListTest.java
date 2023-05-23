package com.smallworldfs.concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class ConcurrentListTest {

    private final Random random = new Random();
    private final ExecutorService executor = Executors.newFixedThreadPool(100);

    @Test
    void concurrent_modifications_and_reads() throws InterruptedException {
        List<Integer> list = new ConcurrentList<>(new ArrayList<>());

        CountDownLatch writes = doManyConcurrent(15, () -> addElement(list));
        CountDownLatch reads = doManyConcurrent(10, () -> printSize(list));
        CountDownLatch iterations = doManyConcurrent(10, () -> printElements(list));

        awaitAll(writes, reads, iterations);

        assertThat(list).hasSize(15);
    }

    @SneakyThrows
    private void addElement(List<Integer> list) {
        System.out.println("Adding element... ");
        list.add(random.nextInt(100));
    }

    private void printSize(List<Integer> list) {
        System.out.println("Collection size " + list.size());
    }

    private void printElements(List<Integer> list) {
        System.out.println("Printing elements... ");
        StringBuilder elements = new StringBuilder("[");

        // noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < list.size(); i++) {
            try {
                elements.append(list.get(i)).append(",");
            } catch (IndexOutOfBoundsException e) { // concurrent modification, lose the update
                break;
            }
        }
        if (elements.length() > 1) {
            elements.deleteCharAt(elements.length() - 1);
        }
        System.out.println(elements.append("]"));
    }

    private CountDownLatch doManyConcurrent(int amount, Runnable runnable) {
        CountDownLatch latch = new CountDownLatch(amount);

        for (int i = 0; i < amount; i++) {
            executor.submit(() -> {
                try {
                    sleepRandom();
                    runnable.run();
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return latch;
    }

    private void awaitAll(CountDownLatch... latches) throws InterruptedException {
        for (CountDownLatch latch : latches) {
            latch.await();
        }
    }

    private void sleepRandom() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }
}
