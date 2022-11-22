package com.smallworldfs.tennis.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Player {

    private final String name;

    private int points;

    public void wonPoint() {
        points++;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public boolean hasSamePointsAs(Player other) {
        return other.points == points;
    }

    public boolean hasWonOver(Player other) {
        return points - other.points > 1 && points > 3;
    }

    public boolean hasAdvantageOver(Player other) {
        return points - other.points == 1 && points > 3;
    }

    public boolean hasMorePointsThan(Player other) {
        return points > other.points;
    }
}
