package day9;

import shared.FileReaderUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> input = new FileReaderUtil().readFile("day9_input.txt");

        part2(input);
    }

    private static void part1(List<String> input) {
        RopeGrid ropeGrid = new RopeGrid(input, 1);

        ropeGrid.traverseCommands();

        System.out.println("Total nodes visited by tail is: " + ropeGrid.getNumberOfCoordinatesVisitedByTail());
    }

    private static void part2(List<String> input) {
        RopeGrid ropeGrid = new RopeGrid(input, 9);

        ropeGrid.traverseCommands();

        System.out.println("Total nodes visited by tail is: " + ropeGrid.getNumberOfCoordinatesVisitedByTail());
    }

}
