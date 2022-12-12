package day9;

import day9.DirectionUtil.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class RopeGrid {
    private final List<String> commands;

    private final int tailLength;
    private final Stack<Knot> visitedCoordinatesTail = new Stack<>();
    private final List<Knot> snake = new ArrayList<>();
    private Knot headKnot = new Knot(0, 0);

    public RopeGrid(List<String> commands, int tailLength) {
        this.commands = commands;
        this.tailLength = tailLength;
        snake.add(headKnot);
        for (int i = 0; i < tailLength; i++) {
            Knot knot = new Knot(0, 0);
            snake.get(i).setChild(knot);
            knot.setParent(snake.get(i));
            snake.add(knot);
        }
    }

    protected int getNumberOfCoordinatesVisitedByTail() {
        return new HashSet<>(visitedCoordinatesTail.stream().toList()).size();
    }

    protected void traverseCommands() {
        commands.forEach(line -> {
            System.out.println();
            System.out.println(line);
            String[] directionAndStep = line.split(" ");
            executeLine(DirectionUtil.fromString(directionAndStep[0]), Integer.parseInt(directionAndStep[1]));
        });
    }

    private void updateTailKnot(Knot knot) {
        //tail knots
        int currentX = knot.x();
        int currentY = knot.y();

        int headX = knot.getParent().x();
        int headY = knot.getParent().y();

        int deltaX = Math.abs(headX - currentX);
        int deltaY = Math.abs(headY - currentY);

        if (deltaX == 2 && deltaY == 1) {
            knot.setX(currentX + (currentX - headX) / -2);
            knot.setY(currentY + (currentY - headY) * -1);
        } else if (deltaY == 2 && deltaX == 1) {
            knot.setX(currentX + (currentX - headX) * -1);
            knot.setY(currentY + (currentY - headY) / -2);
        } else if (deltaX == 2 && deltaY == 2) {
            knot.setX(currentX + (currentX - headX) / -2);
            knot.setY(currentY + (currentY - headY) / -2);
        } else if (deltaY == 2 && deltaX == 0) {
            knot.setY(currentY + (currentY - headY) / -2);
        } else if (deltaX == 2 && deltaY == 0) {
            knot.setX(currentX + (currentX - headX) / -2);
        }
    }

    private void dragKnotBehind(Knot draggingKnot) {
        updateTailKnot(draggingKnot);

        Knot followingKnot = draggingKnot.getChild();

        if (followingKnot != null) {
            dragKnotBehind(followingKnot);
        }
    }

    private void executeLine(Direction direction, int steps) {
        for (int i = steps; i > 0; i--) {
            headKnot.setX(headKnot.x() + direction.getXDelta());
            headKnot.setY(headKnot.y() + direction.getYDelta());
            dragKnotBehind(headKnot.getChild());
            printSnake();
            visitedCoordinatesTail.add(new Knot(snake.get(snake.size() - 1).x(), snake.get(snake.size() - 1).y()));
        }
    }

    public Stack<Knot> getVisitedCoordinatesTail() {
        return visitedCoordinatesTail;
    }

    protected void printSnake() {
        System.out.println("Snake");
        System.out.println(snake);
        System.out.println();
    }

    protected void printTail() {
        System.out.println("Tail");
        HashSet<Knot> set = new HashSet<>(visitedCoordinatesTail.stream().toList());
        System.out.println(set);
        printGridForCoordinateCollection(new ArrayList<>(set));
        System.out.println();
    }

    protected void printGridForCoordinateCollection(List<Knot> knots) {

        int minX = knots.stream().mapToInt(knot -> knot.x()).min().orElse(0);
        int minY = knots.stream().mapToInt(knot -> knot.y()).min().orElse(0);

        int maxX = knots.stream().mapToInt(knot -> knot.x()).max().orElse(0);
        int maxY = knots.stream().mapToInt(knot -> knot.y()).max().orElse(0);

        int absMinX = Math.abs(minX);
        int absMinY = Math.abs(minY);

        int grid[][] = new int[absMinY + maxY + 1][absMinX + maxX + 1];

        for (int i = 0; i < knots.size(); i++) {
            Knot knot = knots.get(i);
            if (i == 0) {
                grid[knot.y() + absMinY][(knot.x()) + absMinX] = 2;
            } else {

                grid[knot.y() + absMinY][(knot.x()) + absMinX] = 1;
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
