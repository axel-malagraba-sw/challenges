package com.smallworldfs.tennis;

public class TennisGameScriptLike implements TennisGame {

    private static final String[] SCORE_LABELS = new String[] {"Love", "Fifteen", "Thirty", "Forty"};
    private static final int SCORING_PHASE_CHANGE = 3;

    private final int[] points = new int[2];

    @Override
    public String getScore() {
        if (points[0] == points[1]) {
            return points[0] >= SCORING_PHASE_CHANGE ? "Deuce" : SCORE_LABELS[points[0]] + "-All";
        }
        if (points[0] > points[1] && points[0] > SCORING_PHASE_CHANGE) {
            return mapWinningLabel() + " player1";
        }
        if (points[1] > points[0] && points[1] > SCORING_PHASE_CHANGE) {
            return mapWinningLabel() + " player2";
        }
        return SCORE_LABELS[points[0]] + "-" + SCORE_LABELS[points[1]];
    }

    private String mapWinningLabel() {
        return Math.abs(points[0] - points[1]) > 1 ? "Win for" : "Advantage";
    }

    @Override
    public void wonPoint(String player) {
        points["player1".equals(player) ? 0 : 1]++;
    }
}
