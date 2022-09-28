package com.smallworldfs.anagram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smallworldfs.anagrams.AnagramChallenge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AnagramChallengeTest {

    private final AnagramChallenge challenge = new AnagramChallenge();

    @ParameterizedTest
    @CsvSource(value = {"null,ALGO", "ALGO,null", "null,null"}, nullValues = "null")
    void must_return_false_if_a_parameter_is_null(String left, String right) {
        assertIsNotAnagram(left, right);
    }

    @Test
    void must_return_false_for_inputs_with_different_size() {
        assertIsNotAnagram("ALGO", "ALGO2");
    }

    @Test
    void must_return_false_when_both_words_are_the_same() {
        assertIsNotAnagram("ALGO", "ALGO");
    }

    @Test
    void must_return_true_when_anagrams_contain_same_char_in_same_position() {
        assertIsAnagram("ALGO", "LAGO");
    }

    @Test
    void must_return_false_when_words_are_not_anagrams() {
        assertIsNotAnagram("HELLO", "TIMED");
    }

    @ParameterizedTest
    @CsvSource(value = {"COSA,SACO", "SALE,ALES", "EAR,ARE"})
    void must_return_true_when_words_are_anagrams(String left, String right) {
        assertIsAnagram(left, right);
    }

    @Test
    void must_not_be_case_sensitive() {
        assertIsAnagram("coSA", "SaCo");
    }

    private void assertIsNotAnagram(String left, String right) {
        assertFalse(isAnagram(left, right));
    }

    private void assertIsAnagram(String left, String right) {
        assertTrue(isAnagram(left, right));
    }

    private boolean isAnagram(String left, String right) {
        return challenge.isAnagram(left, right);
    }
}
