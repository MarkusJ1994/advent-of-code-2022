package rocker_paper_scissors.types;

public interface RPSType {

    int getValue();

    int scoreAgainst(RPSType challenger);

    RPSType deriveChallengerForDesiredScore(int desiredScore);
}
