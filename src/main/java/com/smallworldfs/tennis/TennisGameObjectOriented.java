package com.smallworldfs.tennis;

import com.smallworldfs.tennis.model.Player;

public class TennisGameObjectOriented implements TennisGame {

    public String P1res = "";
    public String P2res = "";

    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");

    public String getScore() {
        String score = "";
        if (player1.getPoints() == player2.getPoints() && player1.getPoints() < 4) {
            if (player1.getPoints() == 0)
                score = "Love";
            if (player1.getPoints() == 1)
                score = "Fifteen";
            if (player1.getPoints() == 2)
                score = "Thirty";
            score += "-All";
        }
        if (player1.getPoints() == player2.getPoints() && player1.getPoints() >= 3)
            score = "Deuce";

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

        if (player1.getPoints() > player2.getPoints() && player2.getPoints() >= 3) {
            score = "Advantage player1";
        }

        if (player2.getPoints() > player1.getPoints() && player1.getPoints() >= 3) {
            score = "Advantage player2";
        }

        if (player1.getPoints() >= 4 && player2.getPoints() >= 0 && (player1.getPoints() - player2.getPoints()) >= 2) {
            score = "Win for player1";
        }
        if (player2.getPoints() >= 4 && player1.getPoints() >= 0 && (player2.getPoints() - player1.getPoints()) >= 2) {
            score = "Win for player2";
        }
        return score;
    }

    public void wonPoint(String playerName) {
        (player1.hasName(playerName) ? player1 : player2).wonPoint();
    }
}
