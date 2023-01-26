package com.smallworldfs.tasklist.cli.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.RequiredArgsConstructor;

public class TestOutput implements Output {

    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void write(String text) {
        stringBuilder.append(text);
    }

    public static OutputAssert assertThat(TestOutput output) {
        return new OutputAssert(output);
    }

    @RequiredArgsConstructor
    public static class OutputAssert {

        private final TestOutput output;

        public OutputAssert is(String expected) {
            assertEquals(expected + System.lineSeparator(), output.stringBuilder.toString());
            return this;
        }

        public OutputAssert contains(String expected) {
            String errorMessage = "Expected string '" + output.stringBuilder + "' to contain '" + expected + "'";

            assertTrue(output.stringBuilder.toString().contains(expected), errorMessage);
            return this;
        }

        public OutputAssert isEmpty() {
            assertTrue(output.stringBuilder.isEmpty(), "Expected output to be empty");
            return this;
        }
    }
}
