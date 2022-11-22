package com.smallworldfs.tennis.model;

public class BaseState extends GameState {

    public BaseState(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean isCurrentState() {
        return true;
    }

    @Override
    public String format() {
        return mapScore(player1) + "-" + mapScore(player2);
    }
}
