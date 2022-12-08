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

        System.out.println(String.format("Dimensions are [%d][%d]", height, width));

        return new TreeGrid(trees);
    }

    public void applyVisibility() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isOuterRow(y, x)) {
                    grid[y][x].setVisible(true);
                } else {
                    grid[y][x].setVisible(checkXDirection(y, x) || checkYDirection(y, x));
                }
                grid[y][x].setChecked(true);
//                printVisibilityMap();
//                System.out.println();
            }
        }
    }

    private boolean isOuterRow(int y, int x) {
        return y == 0 || x == 0 || y == (getHeight() - 1) || x == (getWidth() - 1);
    }

    protected boolean checkXDirection(int y, int x) {
//        printVisibilityMap();
//        System.out.println(String.format("[%d][%d]", y, x));
//        System.out.println(Arrays.stream(grid[y]).limit(x).map(t -> t.getHeight()).toList());
//        System.out.println(Arrays.stream(grid[y]).skip(x + 1).map(t -> t.getHeight()).toList());
        int leftMax = Arrays.stream(grid[y]).limit(x).mapToInt(t -> t.getHeight()).max().orElse(0);
        int rightMax = Arrays.stream(grid[y]).skip(x + 1).mapToInt(t -> t.getHeight()).max().orElse(0);

//        System.out.println(String.format("leftMax = %d, rightMax = %d", leftMax, rightMax));

        int heightToBeat = Math.min(leftMax, rightMax);

        return grid[y][x].getHeight() > heightToBeat;
    }

    protected boolean checkYDirection(int y, int x) {
//        System.out.println(String.format("[%d][%d]", y, x));
//        System.out.println(Arrays.stream(grid).map(g -> g[x]).limit(y).map(t -> t.getHeight()).toList());
//        System.out.println(Arrays.stream(grid).map(g -> g[x]).skip(y + 1).map(t -> t.getHeight()).toList());
        int leftMax = Arrays.stream(grid).map(g -> g[x]).limit(y).mapToInt(t -> t.getHeight()).max().orElse(0);
        int rightMax = Arrays.stream(grid).map(g -> g[x]).skip(y + 1).mapToInt(t -> t.getHeight()).max().orElse(0);

//        System.out.println(String.format("leftMax = %d, rightMax = %d", leftMax, rightMax));

        int heightToBeat = Math.min(leftMax, rightMax);

        return grid[y][x].getHeight() > heightToBeat;
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

    public void calculateScenicScoreMap(Map<Pair<Integer, Integer>, Integer> coordinateToScore) {
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
        int count = 0;
        switch (dir) {
            case UP -> {
                int goingUp = --y;
                Tree tree;
                while (y >= 0) {
                    tree = grid[goingUp][x];
                    count++;
                    if (tree.isVisible()) {
                        break;
                    }
                    goingUp--;
                }
            }
            case DOWN -> {
                int goingDown = ++y;
                Tree tree;
                while (y < height) {
                    tree = grid[goingDown][x];
                    count++;
                    if (tree.isVisible()) {
                        break;
                    }
                    goingDown++;
                }
            }
            case LEFT -> {
                int goingLeft = --x;
                Tree tree;
                while (x >= 0) {
                    count++;
                    tree = grid[y][goingLeft];
                    if (tree.isVisible()) {
                        break;
                    }
                    goingLeft--;
                }
            }
            case RIGHT -> {
                int goingRight = ++x;
                Tree tree;
                while (x < width) {
                    count++;
                    tree = grid[y][goingRight];
                    if (tree.isVisible()) {
                        break;
                    }
                    goingRight++;
                }
            }
        }
        return count;
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
