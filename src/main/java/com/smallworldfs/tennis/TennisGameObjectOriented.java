package com.smallworldfs.tennis;

import com.smallworldfs.tennis.model.AdvantageState;
import com.smallworldfs.tennis.model.GameState;
import com.smallworldfs.tennis.model.Player;
import com.smallworldfs.tennis.model.TieState;
import com.smallworldfs.tennis.model.WinningState;
import java.util.List;

public class TennisGameObjectOriented implements TennisGame {

    private final Player player1;
    private final Player player2;
    private final List<GameState> specialStates;
    private final GameState baseState;

    public TennisGameObjectOriented() {
        player1 = new Player("player1");
        player2 = new Player("player2");
        baseState = new GameState(player1, player2);
        specialStates = List.of(
                new WinningState(player1, player2),
                new AdvantageState(player1, player2),
                new TieState(player1, player2));
    }

    public String getScore() {
        return specialStates.stream()
                .filter(GameState::isCurrentState)
                .findFirst()
                .orElse(baseState)
                .format();
    }

    public void wonPoint(String playerName) {
        (player1.hasName(playerName) ? player1 : player2).wonPoint();
    }
}
