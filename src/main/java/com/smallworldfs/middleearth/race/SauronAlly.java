package com.smallworldfs.middleearth.race;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SauronAlly implements Race {

    BAD_SOUTHERNERS(2),
    ORCS(2),
    GOBLINS(2),
    WARGS(3),
    TROLLS(5);

    private final int strength;
}
