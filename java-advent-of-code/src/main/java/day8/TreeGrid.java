package day8;

import shared.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class TreeGrid {

    private final Tree[][] grid;
    private final int height;
    private final int width;

    private TreeGrid(Tree[][] grid) {
        this.grid = grid;
        this.height = grid != null ? grid.length : 0;
        this.width = grid[0] != null ? grid[0].length : 0;
    }

    public static TreeGrid constructTreeGrid(List<String> input) {
        int height = input.size();
        int width = input.get(0).length();
        Tree[][] trees = new Tree[height][width];

        for (int y = 0; y < input.size(); y++) {
            char[] chars = input.get(y).toCharArray();
            for (int x = 0; x < chars.length; x++) {
                trees[y][x] = new Tree(Character.getNumericValue(chars[x]));
            }
        }

        return new TreeGrid(trees);
    }

    public void applyVisibility() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isOuterRow(y, x)) {
                    grid[y][x].setVisible(true);
                } else {
                    grid[y][x].setVisible(checkAllDirections(y, x));
                }
                grid[y][x].setChecked(true);
            }
        }
    }

    private boolean isOuterRow(int y, int x) {
        return y == 0 || x == 0 || y == (getHeight() - 1) || x == (getWidth() - 1);
    }

    protected boolean checkAllDirections(int y, int x) {
        int left = distanceToBlockingTree(y, x, Direction.LEFT);
        int right = distanceToBlockingTree(y, x, Direction.RIGHT);
        int up = distanceToBlockingTree(y, x, Direction.UP);
        int down = distanceToBlockingTree(y, x, Direction.DOWN);

        return left == -1 || right == -1 || up == -1 || down == -1;
    }

    public int calculateVisibleTrees() {
        return Arrays.stream(grid).flatMap(arr -> Arrays.stream(arr)).filter(tree -> tree.isVisible()).toList().size();
    }

    public int calculateMaxScenicScore() {
        Map<Pair<Integer, Integer>, Integer> scenicScoreMap = new HashMap<>();

        calculateScenicScoreMap(scenicScoreMap);

        LinkedHashMap<Pair<Integer, Integer>, Integer> sortedBySize = scenicScoreMap.entrySet().stream()
                .sorted(Map.Entry.<Pair<Integer, Integer>, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        Map.Entry<Pair<Integer, Integer>, Integer> bestScenicScore = sortedBySize.entrySet().stream().findFirst().orElse(null);

        return bestScenicScore.getValue();
    }

    private void calculateScenicScoreMap(Map<Pair<Integer, Integer>, Integer> coordinateToScore) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int up = distanceToBlockingTree(y, x, Direction.UP);
                int down = distanceToBlockingTree(y, x, Direction.DOWN);
                int left = distanceToBlockingTree(y, x, Direction.LEFT);
                int right = distanceToBlockingTree(y, x, Direction.RIGHT);

                coordinateToScore.put(new Pair<>(y, x), up * down * left * right);
            }
        }
    }

    private int distanceToBlockingTree(int y, int x, Direction dir) {
        int referenceTreeHeight = grid[y][x].getHeight();
        int count = 0;
        switch (dir) {
            case UP -> {
                int goingUp = --y;
                Tree tree;
                while (goingUp >= 0) {
                    tree = grid[goingUp][x];
                    count++;
                    if (tree.getHeight() >= referenceTreeHeight) {
                        return count;
                    }
                    goingUp--;
                }
            }
            case DOWN -> {
                int goingDown = ++y;
                Tree tree;
                while (goingDown < height) {
                    tree = grid[goingDown][x];
                    count++;
                    if (tree.getHeight() >= referenceTreeHeight) {
                        return count;
                    }
                    goingDown++;
                }
            }
            case LEFT -> {
                int goingLeft = --x;
                Tree tree;
                while (goingLeft >= 0) {
                    count++;
                    tree = grid[y][goingLeft];
                    if (tree.getHeight() >= referenceTreeHeight) {
                        return count;
                    }
                    goingLeft--;
                }
            }
            case RIGHT -> {
                int goingRight = ++x;
                Tree tree;
                while (goingRight < width) {
                    count++;
                    tree = grid[y][goingRight];
                    if (tree.getHeight() >= referenceTreeHeight) {
                        return count;
                    }
                    goingRight++;
                }
            }
        }
        //No blocking tree found, signal by -1
        return -1;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void printVisibilityMap() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Tree tree = grid[y][x];
                if (tree.isVisible()) {
                    System.out.printf("\u001B[32m" + "[%d]" + "\u001B[0m", tree.getHeight());
                } else if (tree.isChecked()) {
                    System.out.printf("\u001B[33m" + "[%d]" + "\u001B[0m", tree.getHeight());
                } else {
                    System.out.printf("[%d]", tree.getHeight());
                }
            }
            System.out.println();
        }
    }

    private enum Direction {
        UP, DOWN, RIGHT, LEFT
    }

}
