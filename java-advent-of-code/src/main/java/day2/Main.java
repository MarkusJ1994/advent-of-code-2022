package day2;

import day2.game.RockPaperScissorRound;
import day2.game.RockPaperScissorsGame;
import day2.game.RockPaperScissorsGame.RoundScore;
import day2.types.Paper;
import day2.types.Rock;
import day2.types.RockPaperScissorType;
import day2.types.Scissor;
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
        List<RockPaperScissorRound> rpsRounds = inputToRPSRounds(input);

        System.out.println("Total score:");
        System.out.println(new RockPaperScissorsGame(rpsRounds).calculateMyScore());
    }

    private static List<RockPaperScissorRound> inputToRPSRounds(List<String> input) {
        return input.stream().map(row -> {
            String[] hands = row.split(" ");
            String theirHand = hands[0];
            String myHand = hands[1];
            return new RockPaperScissorRound(inputToOption(theirHand), inputToOption(myHand));
        }).toList();
    }

    private static void part2(List<String> input) {
        //Map A -> Rock, B -> Paper, C -> Scissors
        //Map X -> Lose, Y -> Draw, Z -> Win
        List<RockPaperScissorRound> rpsRounds = inputToDerivedRPSRounds(input);

        System.out.println("Total score:");
        System.out.println(new RockPaperScissorsGame(rpsRounds).calculateMyScore());
    }

    private static List<RockPaperScissorRound> inputToDerivedRPSRounds(List<String> input) {
        return input.stream().map(row -> {
            String[] hands = row.split(" ");
            String theirHand = hands[0];
            String myResult = hands[1];
            RockPaperScissorType theirRPSHand = inputToOption(theirHand);
            return new RockPaperScissorRound(theirRPSHand, inputToDerivedOption(theirRPSHand, myResult));
        }).toList();
    }

    private static RockPaperScissorType inputToDerivedOption(RockPaperScissorType theirHand, String inputResult) {
        //Reversed win/lose since we have theirr hand and want to calculate our results
        return switch (inputResult) {
            case "X" -> theirHand.challengerByScore(RoundScore.Win);
            case "Y" -> theirHand.challengerByScore(RoundScore.Draw);
            case "Z" -> theirHand.challengerByScore(RoundScore.Lose);
            default -> throw new IllegalArgumentException("None matching");
        };
    }

    private static RockPaperScissorType inputToOption(String inputHand) {
        return switch (inputHand) {
            case "A", "X" -> new Rock();
            case "B", "Y" -> new Paper();
            case "C", "Z" -> new Scissor();
            default -> throw new IllegalArgumentException("None matching");
        };
    }

}
