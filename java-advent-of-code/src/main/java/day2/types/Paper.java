package day2.types;

import day2.game.RockPaperScissorsGame.RoundScore;

public class Paper implements RockPaperScissorType {

    private int value = 2;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public RoundScore scoreByChallenger(RockPaperScissorType challenger) {
        if (challenger instanceof Paper) {
            return RoundScore.Draw;
        } else if (challenger instanceof Scissor) {
            return RoundScore.Lose;
        } else {
            return RoundScore.Win;
        }
    }

    @Override
    public RockPaperScissorType challengerByScore(RoundScore desiredScore) {
        return switch (desiredScore) {
            case Lose -> new Scissor();
            case Draw -> new Paper();
            case Win -> new Rock();
        };
    }
}
