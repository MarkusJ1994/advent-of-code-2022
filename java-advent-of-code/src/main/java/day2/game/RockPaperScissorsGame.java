package day2.game;

import java.util.List;

public class RockPaperScissorsGame {

    private List<RockPaperScissorRound> rounds;

    public RockPaperScissorsGame(List<RockPaperScissorRound> rounds) {
        this.rounds = rounds;
    }

    public int calculateMyScore() {
        return rounds.stream().map(round -> calculateRoundScore(round)).reduce(0, (round, total) -> total + round);
    }

    private int calculateRoundScore(RockPaperScissorRound round) {
        return round.myHand().scoreByChallenger(round.theirHand()).getScore() + round.myHand().getValue();
    }

    public enum RoundScore {
        Win(6), Lose(0), Draw(3);

        final int score;

        RoundScore(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }

}
