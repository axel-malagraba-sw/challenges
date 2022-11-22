package com.smallworldfs.tennis.model.state;

import com.smallworldfs.tennis.model.Player;

class AdvantageState extends AbstractGameState {

    AdvantageState(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean isCurrentState() {
        return player1.hasAdvantageOver(player2) || player2.hasAdvantageOver(player1);
    }

    @Override
    public String formatPoints() {
        return "Advantage " + getWinningPlayer().getName();
    }
}
