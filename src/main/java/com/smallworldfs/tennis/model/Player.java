package com.smallworldfs.tennis.model;

import lombok.Data;

@Data
public class Player {

    private final String name;

    private int points;

    public void wonPoint() {
        points++;
    }
}
