package com.smallworldfs.tennis.model;

public class WinningState extends GameState {

    public WinningState(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean isCurrentState() {
        return player1.hasWonOver(player2) || player2.hasWonOver(player1);
    }

    @Override
    public String format() {
        return "Win for " + (player1.hasWonOver(player2) ? player1 : player2).getName();
    }
}
