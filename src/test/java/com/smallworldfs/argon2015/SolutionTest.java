package com.smallworldfs.argon2015;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SolutionTest {

    static Stream<Arguments> inputsAndOutputs() {
        return Stream.of(
                Arguments.of("11010011", 7),
                Arguments.of("0000010000", 6),
                Arguments.of("10", 0),
                Arguments.of("11", 0));
    }

    @ParameterizedTest
    @MethodSource("inputsAndOutputs")
    void should_return_expected_result(String input, int expectedResult) {
        assertEquals(expectedResult, solve(input));
    }

    private int solve(String input) {
        return new Solution().solution(input.chars().map(Character::getNumericValue).toArray());
    }
}
