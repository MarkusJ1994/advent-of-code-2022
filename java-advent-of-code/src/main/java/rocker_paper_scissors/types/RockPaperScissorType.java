package rocker_paper_scissors.types;

public interface RockPaperScissorType {

    int getValue();

    int scoreAgainst(RockPaperScissorType challenger);

    RockPaperScissorType deriveChallengerForDesiredScore(int desiredScore);
}
