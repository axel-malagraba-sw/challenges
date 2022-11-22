package com.smallworldfs.tennis.model;

public class AdvantageState extends GameState {

    public AdvantageState(Player player1, Player player2) {
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
