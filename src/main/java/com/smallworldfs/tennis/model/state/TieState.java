package com.smallworldfs.tennis.model.state;

import com.smallworldfs.tennis.model.Player;

class TieState extends AbstractGameState {

    TieState(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean isCurrentState() {
        return player1.hasSamePointsAs(player2);
    }

    @Override
    public String formatPoints() {
        if (player1.isPastOrAtThirdPoint()) {
            return "Deuce";
        }
        return formatPoints(player1) + "-All";
    }
}
