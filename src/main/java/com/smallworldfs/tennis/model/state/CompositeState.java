package com.smallworldfs.tennis.model.state;

import com.smallworldfs.tennis.model.Player;
import java.util.List;

public class CompositeState implements GameState {

    private final List<GameState> states;

    public CompositeState(Player player1, Player player2) {
        this.states = List.of(
                new TieState(player1, player2),
                new EarlyGameState(player1, player2),
                new WinningState(player1, player2),
                new AdvantageState(player1, player2));
    }

    @Override
    public boolean isCurrentState() {
        return true;
    }

    @Override
    public String formatPoints() {
        return resolveCurrentState().formatPoints();
    }

    private GameState resolveCurrentState() {
        return states.stream()
                .filter(GameState::isCurrentState)
                .findFirst()
                .orElseThrow();
    }
}
