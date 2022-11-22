package com.smallworldfs.tennis.model.state;

import com.smallworldfs.tennis.model.Player;

class WinningState extends AbstractGameState {

    WinningState(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean isCurrentState() {
        return player1.hasWonOver(player2) || player2.hasWonOver(player1);
    }

    @Override
    public String formatPoints() {
        return "Win for " + getWinningPlayer().getName();
    }
}
