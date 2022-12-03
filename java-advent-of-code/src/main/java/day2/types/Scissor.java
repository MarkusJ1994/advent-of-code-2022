package day2.types;

import day2.game.RockPaperScissorsGame.RoundScore;

public class Scissor implements RockPaperScissorType {

    private int value = 3;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public RoundScore scoreByChallenger(RockPaperScissorType challenger) {
        if (challenger instanceof Scissor) {
            return RoundScore.Draw;
        } else if (challenger instanceof Rock) {
            return RoundScore.Lose;
        } else {
            return RoundScore.Win;
        }
    }

    @Override
    public RockPaperScissorType challengerByScore(RoundScore desiredScore) {
        return switch (desiredScore) {
            case Lose -> new Rock();
            case Draw -> new Scissor();
            case Win -> new Paper();
        };
    }
}
