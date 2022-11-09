package com.smallworldfs.gameofcodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SolutionTest {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Random random = new Random();

    static Stream<Arguments> exampleInputs() {
        return Stream.of(
                Arguments.of("aabacbba", 6),
                Arguments.of("aabxbaba", 6),
                Arguments.of("xxxyxxyyyxyyy", 11),
                Arguments.of("atheaxbtheb", 5));
    }

    @Test
    void must_return_string_length_when_only_one_character() {
        assertStringResultIsEqualTo("aaaaaaaa", 8);
    }

    @ParameterizedTest
    @MethodSource("exampleInputs")
    void must_work_on_example_inputs(String input, int expectedResult) {
        assertStringResultIsEqualTo(input, expectedResult);
    }

    @Test
    void must_return_most_frequent_plus_1_when_letters_are_repated_one_by_one() {
        assertStringResultIsEqualTo("abababababababababababababababababababa", 21);
    }

    @Test
    void must_return_result_for_aba_input() {
        assertStringResultIsEqualTo("asdabatasafajapapppssabnbabbybbwbtbzbybbpiohjeaaaaaqapazauaapaa", 32);
    }

    @Test
    void xxxxxxxyyyyyyyxzzz_should_return_17() {
        assertStringResultIsEqualTo("xxxxxxxyyyyyyyxzzz", 17);
    }

    @Test
    void xxxabyyycdkkk_should_return_9() {
        assertStringResultIsEqualTo("xxxabyyycdkkk", 9);
    }

    @Test
    void asdfghjlkasdfghjlkasdfghljasdfgahjlsdfgaljasfkgjsjkdfjglksdjfglksjdlkfgjlskjfg_should_return_3() {
        assertStringResultIsEqualTo(
                "asdfghjlkasdfghjlkasdfghljasdfgahjlsdfgaljasfkgjsjkdfjglksdjfglksjdlkfgjlskjfg", 15);
    }

    @Test
    void ababababababababababababababababababababababababaxxxx_must_return_29() {
        assertStringResultIsEqualTo("ababababababababababababababababababababababababaxxxx", 29);
    }

    @Test
    void qwqertyiuiqoipa_must_return_6() {
        assertStringResultIsEqualTo("qwqertyiuiqoipa", 6);
    }

    @Test
    void must_scale_to_large_input() {
        String input = "sdfjhsmkdhrmisjhncrisnugricustgfnusgefnuosgynefbuisgbdufygsoeufgbsoieufsoekufions"
                + "ueyfnicsuehfiusegfukhsgefiljsghejfhgseukfhgsuefg".repeat(23);

        assertStringResultIsEqualTo(input, 175);
    }

    @Test
    void must_process_500_character_input_in_less_than_100_millis() {
        await(() -> solve(randomInput(500)), Duration.ofMillis(100));
    }

    @Test
    void must_process_1000_character_input_in_less_than_100_millis() {
        await(() -> solve(randomInput(1000)), Duration.ofMillis(100));
    }

    @Test
    void aaaaabbbb_must_return_9() {
        assertStringResultIsEqualTo("aaaaabbbb", 9);
    }

    @Test
    void aaaaabbbbx_must_return_10() {
        assertStringResultIsEqualTo("aaaaabbbbx", 10);
    }

    @Test
    void aaaaabaaaa_must_return_10() {
        assertStringResultIsEqualTo("aaaaabaaaa", 10);
    }

    @Test
    void dadadbdacacacbc_must_return_9() {
        assertStringResultIsEqualTo("dadadbdacacacbc", 9);
    }

    @Test
    void test_100000_characters_finish_in_under_7_seconds() {
        await(() -> solve(randomInput(100000)), Duration.ofSeconds(7));
    }

    @Test
    void test_200000_characters_finish_in_under_7_seconds() {
        await(() -> solve(randomInput(200000)), Duration.ofSeconds(7));
    }

    @Test
    void test_repeated_200000_characters_finish_in_under_6_seconds() {
        assertStringResultIsEqualTo("abc".repeat(50000), 50002);
    }

    @Test
    void test_repeated_random_200000_characters_finish_in_under_7_seconds() {
        await(() -> solve("skjdbfsjdv".repeat(20000)), Duration.ofSeconds(7));
    }

    @Test
    void large_aba() {
        String input = "aaaonmaala".repeat(6000)
                + "bbhbobbhgb".repeat(6000)
                + "aaajjaanna".repeat(6000);

        assertStringResultIsEqualTo(input, 108000);
    }

    private String randomInput(int size) {
        return IntStream.range(0, size)
                .mapToObj(index -> String.valueOf(randomChar()))
                .collect(Collectors.joining());
    }

    private char randomChar() {
        return (char) (random.nextInt(26) + 'a');
    }

    public void assertStringResultIsEqualTo(String string, int expectedResult) {
        await(() -> assertEquals(expectedResult, solve(string)), Duration.ofSeconds(6));
    }

    private void await(Runnable runnable, Duration duration) {
        try {
            executor.submit(runnable).get(duration.toMillis(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int solve(String string) {
        return new Solution().solution(string);
    }
}
