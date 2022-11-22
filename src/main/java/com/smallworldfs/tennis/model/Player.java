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
        return calculatePointDiff(other) > 1 && isPastThirdPoint();
    }

    public boolean hasAdvantageOver(Player other) {
        return calculatePointDiff(other) == 1 && isPastThirdPoint();
    }

    private int calculatePointDiff(Player other) {
        return points - other.points;
    }

    public boolean hasMorePointsThan(Player other) {
        return points > other.points;
    }

    public boolean isPastThirdPoint() {
        return points > 3;
    }

    public boolean isPastOrAtThirdPoint() {
        return points >= 3;
    }
}
