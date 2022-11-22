package com.smallworldfs.tennis.model.state;

import com.smallworldfs.tennis.model.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractGameState implements GameState {

    private static final String[] POINT_LABELS = new String[] {"Love", "Fifteen", "Thirty", "Forty"};

    protected final Player player1;
    protected final Player player2;

    protected String formatPoints(Player player) {
        return POINT_LABELS[player.getPoints()];
    }

    protected Player getWinningPlayer() {
        return player2.hasMorePointsThan(player1) ? player2 : player1;
    }
}
