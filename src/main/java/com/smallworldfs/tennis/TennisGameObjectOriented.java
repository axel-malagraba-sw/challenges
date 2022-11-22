package com.smallworldfs.tennis;

import com.smallworldfs.tennis.model.AdvantageState;
import com.smallworldfs.tennis.model.BaseState;
import com.smallworldfs.tennis.model.GameState;
import com.smallworldfs.tennis.model.Player;
import com.smallworldfs.tennis.model.TieState;
import com.smallworldfs.tennis.model.WinningState;

public class TennisGameObjectOriented implements TennisGame {

    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");

    public String getScore() {
        String score = "";

        score = getTieScore(score);
        score = getAdvantageScore(score);
        score = getWinningScore(score);

        if (score.isEmpty()) {
            score = getBaseScore(score);
        }
        return score;
    }

    private String getBaseScore(String score) {
        return checkState(score, new BaseState(player1, player2));
    }

    private String getAdvantageScore(String score) {
        return checkState(score, new AdvantageState(player1, player2));
    }

    private String getWinningScore(String score) {
        return checkState(score, new WinningState(player1, player2));
    }

    private String getTieScore(String score) {
        return checkState(score, new TieState(player1, player2));
    }

    private String checkState(String score, GameState state) {
        if (state.isCurrentState()) {
            return state.format();
        }
        return score;
    }

    public void wonPoint(String playerName) {
        (player1.hasName(playerName) ? player1 : player2).wonPoint();
    }
}
