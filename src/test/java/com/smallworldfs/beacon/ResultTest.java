package com.smallworldfs.beacon;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class ResultTest {

    @Test
    void returns_count_when_beacon_intersects_network() {
        assertThat(Result.countNodesInBeacon(0, 0, 1, 2, 0, 0, 1))
                .isEqualTo(3);
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void returns_count_for_large_network_with_small_beacon() {
        assertThat(Result.countNodesInBeacon(0, 0, 10000, 10000, 0, 0, 3))
                .isEqualTo(11);
    }

    @Test
    void returns_count_when_beacon_contains_network() {
        assertThat(Result.countNodesInBeacon(0, 0, 5, 5, 0, 0, 500))
                .isEqualTo(36);
    }

    @Test
    void returns_zero_when_network_and_beacon_do_not_intersect() {
        assertThat(Result.countNodesInBeacon(25, 25, 100, 100, 0, 0, 10))
                .isZero();
    }
}
