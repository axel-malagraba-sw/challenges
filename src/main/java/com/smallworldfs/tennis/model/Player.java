package com.smallworldfs.tennis.model;

import lombok.Data;

@Data
public class Player {

    private final String name;

    private int points;

    public void wonPoint() {
        points++;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }
}
