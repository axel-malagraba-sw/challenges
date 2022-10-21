package com.smallworldfs.middleearth;

import static com.smallworldfs.middleearth.battle.Outcome.DRAW;
import static com.smallworldfs.middleearth.battle.Outcome.SAURON_ADVERSARIES_WIN;
import static com.smallworldfs.middleearth.battle.Outcome.SAURON_ALLIES_WIN;
import static com.smallworldfs.middleearth.race.SauronAdversary.DWARVES;
import static com.smallworldfs.middleearth.race.SauronAdversary.ELVES;
import static com.smallworldfs.middleearth.race.SauronAdversary.GOOD_SOUTHERNERS;
import static com.smallworldfs.middleearth.race.SauronAdversary.HARFOOTS;
import static com.smallworldfs.middleearth.race.SauronAdversary.NUMERONEANS;
import static com.smallworldfs.middleearth.race.SauronAlly.BAD_SOUTHERNERS;
import static com.smallworldfs.middleearth.race.SauronAlly.GOBLINS;
import static com.smallworldfs.middleearth.race.SauronAlly.ORCS;
import static com.smallworldfs.middleearth.race.SauronAlly.TROLLS;
import static com.smallworldfs.middleearth.race.SauronAlly.WARGS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smallworldfs.middleearth.army.Army;
import com.smallworldfs.middleearth.battle.Outcome;
import com.smallworldfs.middleearth.race.SauronAdversary;
import com.smallworldfs.middleearth.race.SauronAlly;
import org.junit.jupiter.api.Test;

public class MiddleEarthChallengeTest {

    private final MiddleEarthChallenge challenge = new MiddleEarthChallenge();

    @Test
    void one_harfoot_should_lose_against_one_orc() {
        Army<SauronAlly> sauronAllies = Army.of(1, ORCS);
        Army<SauronAdversary> sauronAdversaries = Army.of(1, HARFOOTS);

        assertSauronAlliesWin(sauronAllies, sauronAdversaries);
    }

    @Test
    void one_numeronean_and_two_dwarves_should_win_against_one_goblin_and_one_warg() {
        Army<SauronAlly> sauronAllies = Army.of(1, GOBLINS).and(1, WARGS);
        Army<SauronAdversary> sauronAdversaries = Army.of(1, NUMERONEANS).and(2, DWARVES);

        assertSauronAdversariesWin(sauronAllies, sauronAdversaries);
    }

    @Test
    void three_bad_southerners_should_draw_against_three_good_southerners() {
        Army<SauronAlly> sauronAllies = Army.of(3, BAD_SOUTHERNERS);
        Army<SauronAdversary> sauronAdversaries = Army.of(3, GOOD_SOUTHERNERS);

        assertDraw(sauronAllies, sauronAdversaries);
    }

    @Test
    void sauron_adversaries_should_win_full_on_war_if_they_have_more_strength() {
        Army<SauronAlly> sauronAllies = Army
                .of(3, BAD_SOUTHERNERS)
                .and(5, ORCS)
                .and(2, TROLLS)
                .and(4, GOBLINS)
                .and(1, WARGS);
        Army<SauronAdversary> sauronAdversaries = Army
                .of(3, GOOD_SOUTHERNERS)
                .and(5, ELVES)
                .and(2, DWARVES)
                .and(4, NUMERONEANS)
                .and(1, HARFOOTS);

        assertSauronAdversariesWin(sauronAllies, sauronAdversaries);
    }

    @Test
    void cannot_create_an_army_with_0_troops() {
        assertThrows(IllegalArgumentException.class, () -> Army.of(0, GOBLINS));
    }

    @Test
    void cannot_add_negative_troops() {
        assertThrows(IllegalArgumentException.class, () -> Army.of(1, ELVES).and(-1, DWARVES));
    }

    private void assertDraw(Army<SauronAlly> sauronAllies, Army<SauronAdversary> sauronAdversaries) {
        assertEquals(DRAW, getBattleOutcome(sauronAllies, sauronAdversaries));
    }

    private void assertSauronAdversariesWin(Army<SauronAlly> sauronAllies, Army<SauronAdversary> sauronAdversaries) {
        assertEquals(SAURON_ADVERSARIES_WIN, getBattleOutcome(sauronAllies, sauronAdversaries));
    }

    private void assertSauronAlliesWin(Army<SauronAlly> sauronAllies, Army<SauronAdversary> sauronAdversaries) {
        assertEquals(SAURON_ALLIES_WIN, getBattleOutcome(sauronAllies, sauronAdversaries));
    }

    private Outcome getBattleOutcome(Army<SauronAlly> sauronAllies, Army<SauronAdversary> sauronAdversaries) {
        return challenge.simulateBattle(sauronAllies, sauronAdversaries);
    }
}
