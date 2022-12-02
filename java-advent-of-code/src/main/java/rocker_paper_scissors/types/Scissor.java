package rocker_paper_scissors.types;

import rocker_paper_scissors.RockPaperScissorsGame;

public class Scissor implements RPSType {

    private int value = 3;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int scoreAgainst(RPSType challenger) {
        if (challenger instanceof Scissor) {
            return RockPaperScissorsGame.draw;
        } else if (challenger instanceof Rock) {
            return RockPaperScissorsGame.lose;
        } else {
            return RockPaperScissorsGame.win;
        }
    }

    @Override
    public RPSType deriveChallengerForDesiredScore(int desiredScore) {
        return switch (desiredScore) {
            case RockPaperScissorsGame.lose -> new Rock();
            case RockPaperScissorsGame.draw -> new Scissor();
            case RockPaperScissorsGame.win -> new Paper();
            default -> throw new IllegalArgumentException("Unmapped score");
        };
    }
}
