package com.smallworldfs.ratio;

import static java.util.Arrays.stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberRatios {

    private static final int POSITIVE = 1;
    private static final int NEGATIVE = -1;
    private static final int ZERO = 0;
    private static final int[] TYPES = new int[] {POSITIVE, NEGATIVE, ZERO};
    private static final int SCALE = 6;

    public static void countRatios(int[] array) {
        print(array.length, countByType(array));
    }

    private static Map<Integer, Long> countByType(int[] array) {
        return stream(array).boxed().collect(Collectors.groupingBy(k -> k.compareTo(ZERO), Collectors.counting()));
    }

    private static void print(int total, Map<Integer, Long> countByType) {
        stream(TYPES).mapToObj(type -> calculateRatio(total, countByType.get(type))).forEach(System.out::println);
    }

    private static String calculateRatio(int total, Long count) {
        if (total == ZERO) {
            return String.valueOf(ZERO);
        }
        return BigDecimal.valueOf(count)
                .divide(BigDecimal.valueOf(total), SCALE, RoundingMode.HALF_UP)
                .toPlainString();
    }
}
