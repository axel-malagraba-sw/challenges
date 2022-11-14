package com.smallworldfs.socks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test
    void must_return_max_paired_clean_socks_if_washing_capacity_is_0() {
        assertThatSolution()
                .withDirtySocks(new int[] {4, 1, 1, 1, 2, 3})
                .withCleanSocks(new int[] {4, 4, 4, 3, 2, 2, 3, 3, 1, 1, 1})
                .isEqualTo(4);
    }

    @Test
    void must_return_max_paired_clean_socks_if_no_dirty_socks() {
        assertThatSolution()
                .withWashingCapacity(10)
                .withCleanSocks(new int[] {4, 4, 4, 3, 2, 2, 3, 3, 1, 1, 1})
                .isEqualTo(4);
    }

    @Test
    void must_prioritize_odd_clean_socks_when_cleaning() {
        assertThatSolution()
                .withWashingCapacity(2)
                .withDirtySocks(new int[] {4, 1, 1, 1, 2, 3})
                .withCleanSocks(new int[] {4, 4, 4, 3, 2, 2, 3, 3, 1, 1, 1})
                .isEqualTo(6);
    }

    @Test
    void must_stop_when_no_more_dirty_socks() {
        assertThatSolution()
                .withWashingCapacity(100)
                .withDirtySocks(new int[] {4, 1, 1, 1, 2, 2, 3, 3})
                .withCleanSocks(new int[] {4, 4, 4, 3, 2, 2, 3, 3, 1, 1, 1})
                .isEqualTo(9);
    }

    @Test
    void must_return_0_for_empty_inputs() {
        assertThatSolution().isEqualTo(0);
    }

    SolutionAssert assertThatSolution() {
        return new SolutionAssert();
    }

    @With
    @NoArgsConstructor
    @AllArgsConstructor
    static class SolutionAssert {

        private int washingCapacity;
        private int[] cleanSocks = new int[0];
        private int[] dirtySocks = new int[0];

        public void isEqualTo(int expectedResult) {
            assertEquals(expectedResult, new Solution().solution(washingCapacity, cleanSocks, dirtySocks));
        }
    }
}
