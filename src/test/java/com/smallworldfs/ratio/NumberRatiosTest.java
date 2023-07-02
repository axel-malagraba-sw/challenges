package com.smallworldfs.ratio;

import org.junit.jupiter.api.Test;

class NumberRatiosTest {

    @Test
    void array_with_one_of_each() {
        int[] array = new int[] {-1, 0, 1};

        NumberRatios.countRatios(array);
    }
}
