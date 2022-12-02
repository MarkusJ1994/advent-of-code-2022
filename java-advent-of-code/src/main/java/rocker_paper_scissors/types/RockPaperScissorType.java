package rocker_paper_scissors.types;

import rocker_paper_scissors.game.RockPaperScissorsGame.RoundScore;

public interface RockPaperScissorType {

    int getValue();

    RoundScore scoreByChallenger(RockPaperScissorType challenger);

    RockPaperScissorType challengerByScore(RoundScore desiredScore);
}
