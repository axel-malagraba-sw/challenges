package com.smallworldfs.tennis.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class GameState {

    private static final String[] SCORE_LABELS = new String[] {"Love", "Fifteen", "Thirty", "Forty"};

    protected final Player player1;
    protected final Player player2;

    public abstract boolean isCurrentState();

    public abstract String format();

    protected String mapScore(Player player) {
        return SCORE_LABELS[player.getPoints()];
    }
}
