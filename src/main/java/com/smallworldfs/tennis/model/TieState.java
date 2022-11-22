package com.smallworldfs.tennis.model;

public class TieState extends GameState {

    public TieState(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean isCurrentState() {
        return player1.hasSamePointsAs(player2);
    }

    @Override
    public String formatPoints() {
        if (player1.getPoints() >= 3) {
            return "Deuce";
        }
        return formatPoints(player1) + "-All";
    }
}
