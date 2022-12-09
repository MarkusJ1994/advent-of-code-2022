package day9;

import day9.DirectionUtil.Direction;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RopeGrid {
    private final List<String> commands;

    private final int tailLength;
    private final Stack<Coordinate> visitedCoordinatesTail = new Stack<>();

    private final LinkedList<Coordinate> snake = new LinkedList<>();

    public RopeGrid(List<String> commands, int tailLength) {
        this.commands = commands;
        this.tailLength = tailLength;
        //Start positions
        Coordinate start = new Coordinate(0, 0);
        snake.add(start);
    }

    protected static boolean distanceBetweenPointsIsOneStepOrLess(Coordinate c1, Coordinate c2) {
        int xDiff = Math.abs(c1.x() - c2.x());
        int yDiff = Math.abs(c1.y() - c2.y());
        return xDiff <= 1 && yDiff <= 1;
    }

    protected int getNumberOfCoordinatesVisitedByTail() {
        return new HashSet<>(visitedCoordinatesTail.stream().toList()).size();
    }

    protected void traverseCommands() {
        commands.forEach(line -> {
            String[] directionAndStep = line.split(" ");
            executeLine(DirectionUtil.fromString(directionAndStep[0]), Integer.parseInt(directionAndStep[1]));
            System.out.println(line);
//            printAllTailSteps();
            System.out.println();
        });
    }


    private void updateTail(Coordinate newHead, Direction direction) {
        //TODO: I need to traverse the tail and update positions to allow tilted axis
        /**
         * Ex:
         * - - - - -        - - - - -        - - - - -        - - - - -         - - - - H
         * - - - - -        - - - - -        - - - - -        - - - - H         - - - - 1
         * - - - - -        - - - - -        - - - - H        - - - 1 -         - - - 2 -
         * - - - - -        - - - - H        - - - 1 -        - - 2 - -         - - 3 - -
         * - - - - H   ->   - - - 1 -   ->   - - 2 - -        - 3 - - -         - 4 - - -
         * 4 3 2 1 -        4 3 2 - -        4 3 - - -        4 - - - -         5 - - - -
         *
         * How to update the nr 2 position?
         *
         * Find line between H and
         */

        //Check the distance between new head and current tail
        int distanceX = newHead.calculateXDistance(snake.peekLast());
        int distanceY = newHead.calculateYDistance(snake.peekLast());


        switch (direction) {
            case LEFT, RIGHT -> {
                if (distanceY >= distanceX) {
                    for (int i = 0; i < distanceX; i++) {
                        Coordinate updateTailNode = snake.get(i);
                        updateTailNode.setX(updateTailNode.x() + direction.getXDelta());
                    }
                } else {
                    snake.addFirst(newHead);
                }
            }
            case UP, DOWN -> {
                if (distanceX >= distanceY) {
                    for (int i = 0; i < distanceY; i++) {
                        Coordinate updateTailNode = snake.get(i);
                        updateTailNode.setY(updateTailNode.y() + direction.getYDelta());
                    }
                } else {
                    snake.addFirst(newHead);
                }
            }
        }
    }

    private void executeLine(Direction direction, int steps) {
        for (int i = steps; i > 0; i--) {
            //Update head
            Coordinate head = snake.peekFirst();
            updateTail(new Coordinate(head.x() + direction.getXDelta(), head.y() + direction.getYDelta()), direction);


            if (snake.size() > (tailLength + 1)) {
                Coordinate lastTailNode = snake.removeLast();
                System.out.println(snake.size());
                System.out.println(String.format("Adding %s to the last tail visit stack", snake.peekLast()));
                visitedCoordinatesTail.add(lastTailNode);
            }

//            printStep();
        }
    }

    public Stack<Coordinate> getVisitedCoordinatesTail() {
        return visitedCoordinatesTail;
    }

    protected void printStep() {
        System.out.println("Tail:" + visitedCoordinatesTail.peek());
        System.out.println();
    }

    protected void printSnake() {
        System.out.println("Snake");
        printGridForCoordinateCollection(snake);
        System.out.println();
    }

    protected void printTail() {
        System.out.println("Tail");
        printGridForCoordinateCollection(visitedCoordinatesTail);
        System.out.println();
    }

    protected void printGridForCoordinateCollection(List<Coordinate> coordinates) {

        int minX = coordinates.stream().mapToInt(coordinate -> coordinate.x()).min().orElse(0);
        int minY = coordinates.stream().mapToInt(coordinate -> coordinate.y()).min().orElse(0);

        int maxX = coordinates.stream().mapToInt(coordinate -> coordinate.x()).max().orElse(0);
        int maxY = coordinates.stream().mapToInt(coordinate -> coordinate.y()).max().orElse(0);

        int absMinX = Math.abs(minX);
        int absMinY = Math.abs(minY);

        int grid[][] = new int[absMinY + maxY + 1][absMinX + maxX + 1];

        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate coordinate = coordinates.get(i);
            if (i == 0) {
                grid[coordinate.y() + absMinY][(coordinate.x()) + absMinX] = 2;
            } else {

                grid[coordinate.y() + absMinY][(coordinate.x()) + absMinX] = 1;
            }
        }

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                System.out.print(grid[y][x] == 2 ? "s" : grid[y][x] == 1 ? "#" : ".");
            }
            System.out.println();
        }
    }

}
