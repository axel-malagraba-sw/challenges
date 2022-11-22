package com.smallworldfs.tennis.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameState {

    private static final String[] SCORE_LABELS = new String[] {"Love", "Fifteen", "Thirty", "Forty"};

    protected final Player player1;
    protected final Player player2;

    public boolean isCurrentState() {
        return true;
    }

    public String format() {
        return mapScore(player1) + "-" + mapScore(player2);
    }

    protected String mapScore(Player player) {
        return SCORE_LABELS[player.getPoints()];
    }
}
