package com.smallworldfs.tennis.model.state;

public interface GameState {

    default boolean isCurrentState() {
        return true;
    }

    String formatPoints();
}
