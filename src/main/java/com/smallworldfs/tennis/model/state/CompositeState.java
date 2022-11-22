package com.smallworldfs.tennis.model.state;

import com.smallworldfs.tennis.model.Player;
import java.util.List;

public class CompositeState extends GameState {

    private final List<GameState> states;

    public CompositeState(Player player1, Player player2) {
        super(player1, player2);
        this.states = List.of(
                new WinningState(player1, player2),
                new AdvantageState(player1, player2),
                new TieState(player1, player2),
                new GameState(player1, player2));
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
