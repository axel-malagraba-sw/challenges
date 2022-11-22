package com.smallworldfs.tennis;

import com.smallworldfs.tennis.model.AdvantageState;
import com.smallworldfs.tennis.model.GameState;
import com.smallworldfs.tennis.model.Player;
import com.smallworldfs.tennis.model.TieState;
import com.smallworldfs.tennis.model.WinningState;

public class TennisGameObjectOriented implements TennisGame {

    public String P1res = "";
    public String P2res = "";

    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");

    public String getScore() {
        String score = "";

        score = getTieScore(score);

        if (player1.getPoints() > 0 && player2.getPoints() == 0) {
            if (player1.getPoints() == 1)
                P1res = "Fifteen";
            if (player1.getPoints() == 2)
                P1res = "Thirty";
            if (player1.getPoints() == 3)
                P1res = "Forty";

            P2res = "Love";
            score = P1res + "-" + P2res;
        }
        if (player2.getPoints() > 0 && player1.getPoints() == 0) {
            if (player2.getPoints() == 1)
                P2res = "Fifteen";
            if (player2.getPoints() == 2)
                P2res = "Thirty";
            if (player2.getPoints() == 3)
                P2res = "Forty";

            P1res = "Love";
            score = P1res + "-" + P2res;
        }

        if (player1.getPoints() > player2.getPoints() && player1.getPoints() < 4) {
            if (player1.getPoints() == 2)
                P1res = "Thirty";
            if (player1.getPoints() == 3)
                P1res = "Forty";
            if (player2.getPoints() == 1)
                P2res = "Fifteen";
            if (player2.getPoints() == 2)
                P2res = "Thirty";
            score = P1res + "-" + P2res;
        }
        if (player2.getPoints() > player1.getPoints() && player2.getPoints() < 4) {
            if (player2.getPoints() == 2)
                P2res = "Thirty";
            if (player2.getPoints() == 3)
                P2res = "Forty";
            if (player1.getPoints() == 1)
                P1res = "Fifteen";
            if (player1.getPoints() == 2)
                P1res = "Thirty";
            score = P1res + "-" + P2res;
        }

        score = getAdvantageScore(score);
        score = getWinningScore(score);

        return score;
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
