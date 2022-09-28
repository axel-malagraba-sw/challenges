package com.smallworldfs;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ChallengeTest {

    private final Challenge challenge = new Challenge();

    @Test
    void test_engine_works() {
        assertNotNull(challenge);
    }
}
