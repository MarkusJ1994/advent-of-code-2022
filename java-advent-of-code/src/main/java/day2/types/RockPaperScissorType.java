package day2.types;

import day2.game.RockPaperScissorsGame.RoundScore;

public interface RockPaperScissorType {

    int getValue();

    RoundScore scoreByChallenger(RockPaperScissorType challenger);

    RockPaperScissorType challengerByScore(RoundScore desiredScore);
}
