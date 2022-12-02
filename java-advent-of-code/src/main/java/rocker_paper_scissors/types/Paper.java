package rocker_paper_scissors.types;

import rocker_paper_scissors.game.RockPaperScissorsGame;

public class Paper implements RockPaperScissorType {

    private int value = 2;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int scoreAgainst(RockPaperScissorType challenger) {
        if (challenger instanceof Paper) {
            return RockPaperScissorsGame.draw;
        } else if (challenger instanceof Scissor) {
            return RockPaperScissorsGame.lose;
        } else {
            return RockPaperScissorsGame.win;
        }
    }

    @Override
    public RockPaperScissorType deriveChallengerForDesiredScore(int desiredScore) {
        return switch (desiredScore) {
            case RockPaperScissorsGame.lose -> new Scissor();
            case RockPaperScissorsGame.draw -> new Paper();
            case RockPaperScissorsGame.win -> new Rock();
            default -> throw new IllegalArgumentException("Unmapped score");
        };
    }
}
