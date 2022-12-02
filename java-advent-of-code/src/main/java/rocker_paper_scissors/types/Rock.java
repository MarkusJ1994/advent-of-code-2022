package rocker_paper_scissors.types;

import rocker_paper_scissors.game.RockPaperScissorsGame.RoundScore;

public class Rock implements RockPaperScissorType {

    private int value = 1;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public RoundScore scoreByChallenger(RockPaperScissorType challenger) {
        if (challenger instanceof Rock) {
            return RoundScore.Draw;
        } else if (challenger instanceof Paper) {
            return RoundScore.Lose;
        } else {
            return RoundScore.Win;
        }
    }

    @Override
    public RockPaperScissorType challengerByScore(RoundScore desiredScore) {
        return switch (desiredScore) {
            case Lose -> new Paper();
            case Draw -> new Rock();
            case Win -> new Scissor();
        };
    }
}
