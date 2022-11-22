package com.smallworldfs.tennis;

import com.smallworldfs.tennis.model.CompositeState;
import com.smallworldfs.tennis.model.GameState;
import com.smallworldfs.tennis.model.Player;

public class TennisGameObjectOriented implements TennisGame {

    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");
    private final GameState state = new CompositeState(player1, player2);

    @Override
    public String getScore() {
        return state.formatPoints();
    }

    @Override
    public void wonPoint(String playerName) {
        getPlayerBy(playerName).wonPoint();
    }

    private Player getPlayerBy(String playerName) {
        return player1.hasName(playerName) ? player1 : player2;
    }
}
