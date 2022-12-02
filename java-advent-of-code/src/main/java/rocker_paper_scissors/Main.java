package rocker_paper_scissors;

import rocker_paper_scissors.types.Paper;
import rocker_paper_scissors.types.RPSType;
import rocker_paper_scissors.types.Rock;
import rocker_paper_scissors.types.Scissor;
import shared.FileReaderUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> input = new FileReaderUtil().readFile("rock_paper_scissors.txt");

        //A Y
        part2(input);
    }

    private static void part1(List<String> input) {
        //Map A -> Rock, B -> Paper, C -> Scissors
        //Map X -> Rock, Y -> Paper, Z -> Scissors
        List<RPSRound> rpsRounds = inputToRPSRounds(input);

        System.out.println("Total score:");
        System.out.println(new RockPaperScissorsGame(rpsRounds).calculateMyScore());
    }

    private static List<RPSRound> inputToRPSRounds(List<String> input) {
        return input.stream().map(row -> {
            String[] hands = row.split(" ");
            String theirHand = hands[0];
            String myHand = hands[1];
            return new RPSRound(inputToOption(theirHand), inputToOption(myHand));
        }).toList();
    }

    private static void part2(List<String> input) {
        //Map A -> Rock, B -> Paper, C -> Scissors
        //Map X -> Lose, Y -> Draw, Z -> Win
        List<RPSRound> rpsRounds = inputToDerivedRPSRounds(input);

        System.out.println("Total score:");
        System.out.println(new RockPaperScissorsGame(rpsRounds).calculateMyScore());
    }

    private static List<RPSRound> inputToDerivedRPSRounds(List<String> input) {
        return input.stream().map(row -> {
            String[] hands = row.split(" ");
            String theirHand = hands[0];
            String myResult = hands[1];
            RPSType theirRPSHand = inputToOption(theirHand);
            return new RPSRound(theirRPSHand, inputToDerivedOption(theirRPSHand, myResult));
        }).toList();
    }

    private static RPSType inputToDerivedOption(RPSType theirHand, String inputResult) {
        //Reversed win/lose since we have theirr hand and want to calculate our results
        return switch (inputResult) {
            case "X" -> theirHand.deriveChallengerForDesiredScore(RockPaperScissorsGame.win);
            case "Y" -> theirHand.deriveChallengerForDesiredScore(RockPaperScissorsGame.draw);
            case "Z" -> theirHand.deriveChallengerForDesiredScore(RockPaperScissorsGame.lose);
            default -> throw new IllegalArgumentException("None matching");
        };
    }

    private static RPSType inputToOption(String inputHand) {
        return switch (inputHand) {
            case "A", "X" -> new Rock();
            case "B", "Y" -> new Paper();
            case "C", "Z" -> new Scissor();
            default -> throw new IllegalArgumentException("None matching");
        };
    }

}
