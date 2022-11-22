package com.smallworldfs.tennis.model.state;

import com.smallworldfs.tennis.model.Player;

public class DefaultGameState extends AbstractGameState {

    public DefaultGameState(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public String formatPoints() {
        return formatPoints(player1) + "-" + formatPoints(player2);
    }
}
