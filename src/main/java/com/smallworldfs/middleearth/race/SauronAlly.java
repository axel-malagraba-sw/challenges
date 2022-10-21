package com.smallworldfs.middleearth.race;

public enum SauronAlly implements Race {

    BAD_SOUTHERNERS(2),
    ORCS(2),
    GOBLINS(2),
    WARGS(3),
    TROLLS(5);

    private final int strength;

    SauronAlly(int strength) {
        this.strength = strength;
    }

    @Override
    public int strength() {
        return strength;
    }
}
