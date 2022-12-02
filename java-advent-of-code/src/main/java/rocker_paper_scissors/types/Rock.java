package rocker_paper_scissors.types;

import rocker_paper_scissors.RockPaperScissorsGame;

public class Rock implements RPSType {

    private int value = 1;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int scoreAgainst(RPSType challenger) {
        if (challenger instanceof Rock) {
            return RockPaperScissorsGame.draw;
        } else if (challenger instanceof Paper) {
            return RockPaperScissorsGame.lose;
        } else {
            return RockPaperScissorsGame.win;
        }
    }

    //Score should probably be an enum to avoid illegal arguments at compile time
    @Override
    public RPSType deriveChallengerForDesiredScore(int desiredScore) {
        return switch (desiredScore) {
            case RockPaperScissorsGame.lose -> new Paper();
            case RockPaperScissorsGame.draw -> new Rock();
            case RockPaperScissorsGame.win -> new Scissor();
            default -> throw new IllegalArgumentException("Unmapped score");
        };
    }
}
