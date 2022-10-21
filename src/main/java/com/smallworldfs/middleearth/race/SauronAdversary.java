package com.smallworldfs.middleearth.race;

public enum SauronAdversary implements Race {

    HARFOOTS(1),
    GOOD_SOUTHERNERS(2),
    DWARVES(3),
    NUMERONEANS(4),
    ELVES(5);

    private final int strength;

    SauronAdversary(int strength) {
        this.strength = strength;
    }

    @Override
    public int strength() {
        return strength;
    }
}
