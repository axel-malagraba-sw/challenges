package com.smallworldfs.tennis.model.state;

import com.smallworldfs.tennis.model.Player;

public class EarlyGameState extends AbstractGameState {

    public EarlyGameState(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean isCurrentState() {
        return !player1.hasSamePointsAs(player2)
                && !player1.isPastThirdPoint()
                && !player2.isPastThirdPoint();
    }

    @Override
    public String formatPoints() {
        return formatPoints(player1) + "-" + formatPoints(player2);
    }
}
