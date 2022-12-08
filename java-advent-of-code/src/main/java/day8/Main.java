package day8;

import shared.FileReaderUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> input = new FileReaderUtil().readFile("day8_trees.txt");
        part2(input);
    }

    private static void part1(List<String> input) {
        TreeGrid treeGrid = TreeGrid.constructTreeGrid(input);
        treeGrid.applyVisibility();
        int visibleTrees = treeGrid.calculateVisibleTrees();

        treeGrid.printVisibilityMap();

        System.out.println("Number of visible trees are: " + visibleTrees);
    }

    private static void part2(List<String> input) {
        TreeGrid treeGrid = TreeGrid.constructTreeGrid(input);
        treeGrid.applyVisibility();

        System.out.println("Best scenic score is: " + treeGrid.calculateMaxScenicScore());
    }

}
