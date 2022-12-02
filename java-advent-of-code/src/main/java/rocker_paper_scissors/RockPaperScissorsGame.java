package rocker_paper_scissors;

import java.util.List;

public class RockPaperScissorsGame {

    public static final int win = 6;
    public static final int draw = 3;
    public static final int lose = 0;

    private List<RPSRound> rounds;

    public RockPaperScissorsGame(List<RPSRound> rounds) {
        this.rounds = rounds;
    }

    public int calculateMyScore() {
        return rounds.stream().map(round -> calculateRoundScore(round)).reduce(0, (round, total) -> total + round);
    }

    private int calculateRoundScore(RPSRound round) {
        return round.myHand().scoreAgainst(round.theirHand()) + round.myHand().getValue();
    }

}
