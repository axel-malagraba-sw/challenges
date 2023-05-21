package com.smallworldfs.concurrency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoundBlockingQueueTest {

    private final int capacity = 10;
    private final BoundBlockingQueue<Integer> queue = new BoundBlockingQueue<>(capacity);
    private final ExecutorService executor = Executors.newFixedThreadPool(capacity * 2);


    @Nested
    class Put {

        @Test
        void throws_illegal_argument_exception_when_element_is_null() {
            assertThrows(IllegalArgumentException.class, () -> queue.put(null));
        }

        @Test
        void increases_size_when_element_added() {
            assertThat(queue.size()).isEqualTo(0);

            queue.put(27);

            assertThat(queue.size()).isEqualTo(1);
        }

        @Test
        void blocks_when_queue_is_full() throws InterruptedException {
            assertThat(putManyConcurrent(capacity + 1).await(1, TimeUnit.SECONDS)).isFalse();
        }

        @Test
        void will_awake_waiting_take_on_empty_queue() throws InterruptedException {
            CountDownLatch puts = putManyConcurrent(10);
            CountDownLatch takes = takeManyConcurrent(11);

            assertThat(puts.await(100, TimeUnit.MILLISECONDS)).isTrue();
            assertThat(takes.await(100, TimeUnit.MILLISECONDS)).isFalse();

            queue.put(1);

            assertThat(takes.await(100, TimeUnit.MILLISECONDS)).isTrue();
        }
    }

    @Nested
    class Take {

        @Test
        void returns_oldest_element() {
            queue.put(1);
            queue.put(2);

            assertThat(queue.take()).isEqualTo(1);
        }

        @Test
        void blocks_when_queue_is_empty() throws InterruptedException {
            putManyConcurrent(5).await();

            assertThat(takeManyConcurrent(6).await(1, TimeUnit.SECONDS)).isFalse();
        }

        @Test
        void will_awake_waiting_put_on_full_queue() throws InterruptedException {
            CountDownLatch puts = putManyConcurrent(11);

            assertThat(puts.await(100, TimeUnit.MILLISECONDS)).isFalse();

            queue.take();

            assertThat(puts.await(100, TimeUnit.MILLISECONDS)).isTrue();
        }
    }

    private CountDownLatch putManyConcurrent(int amount) {
        return doManyConcurrent(amount, () -> queue.put(1));
    }

    private CountDownLatch takeManyConcurrent(int amount) {
        return doManyConcurrent(amount, queue::take);
    }

    private CountDownLatch doManyConcurrent(int amount, Runnable runnable) {
        CountDownLatch latch = new CountDownLatch(amount);

        for (int i = 0; i < amount; i++) {
            executor.submit(() -> {
                runnable.run();
                latch.countDown();
            });
        }
        return latch;
    }
}
