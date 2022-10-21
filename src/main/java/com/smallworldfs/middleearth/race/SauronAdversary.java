package com.smallworldfs.middleearth.race;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SauronAdversary implements Race {

    HARFOOTS(1),
    GOOD_SOUTHERNERS(2),
    DWARVES(3),
    NUMERONEANS(4),
    ELVES(5);

    private final int strength;
}
