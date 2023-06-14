package com.smallworldfs.beacon;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BeaconSignalProcessorTest {

    static Stream<BeaconSignalProcessor> implementations() {
        return Stream.of(
                new RecursiveBeaconSignalProcessor(),
                new IterativeBeaconSignalProcessor(),
                new NaiveBeaconSignalProcessor());
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void returns_count_when_beacon_intersects_network(BeaconSignalProcessor processor) {
        assertThat(processor.countNodesAffectedByBeacon(0, 0, 1, 2, 0, 0, 1))
                .isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void returns_count_for_large_network_with_small_beacon(BeaconSignalProcessor processor) {
        assertThat(processor.countNodesAffectedByBeacon(0, 0, 90000, 90000, 0, 0, 3))
                .isEqualTo(11);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void returns_count_when_beacon_contains_network(BeaconSignalProcessor processor) {
        assertThat(processor.countNodesAffectedByBeacon(0, 0, 5, 5, 0, 0, 500))
                .isEqualTo(36);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void returns_zero_when_network_and_beacon_do_not_intersect(BeaconSignalProcessor processor) {
        assertThat(processor.countNodesAffectedByBeacon(25, 25, 100, 100, 0, 0, 10))
                .isZero();
    }
}
