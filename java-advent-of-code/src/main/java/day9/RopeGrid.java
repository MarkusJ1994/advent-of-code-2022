package day9;

import day9.DirectionUtil.Direction;

import java.util.*;

public class RopeGrid {
    private final List<String> commands;

    private final int tailLength;
    private final Stack<Knot> visitedCoordinatesTail = new Stack<>();

    private final LinkedList<Knot> snake = new LinkedList<>();
    private Knot headKnot;

    public RopeGrid(List<String> commands, int tailLength) {
        this.commands = commands;
        this.tailLength = tailLength;
        //Start positions
        headKnot = new Knot(0, 0);
        snake.addFirst(headKnot);
    }

    protected static boolean distanceBetweenPointsIsOneStepOrLess(Knot c1, Knot c2) {
        int xDiff = Math.abs(c1.x() - c2.x());
        int yDiff = Math.abs(c1.y() - c2.y());
        return xDiff <= 1 && yDiff <= 1;
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

    private void updateKnot(Knot knot, Direction direction) {
        if (knot.getParent() != null) {
            System.out.println("a1:" + knot);
            int currentX = knot.x();
            int currentY = knot.y();

            int draggingX = knot.getParent().x();
            int draggingY = knot.getParent().y();

            int deltaX = Math.abs(draggingX - currentX);
            int deltaY = Math.abs(draggingY - currentY);

            if (deltaX > 1 || deltaY > 1) {
                System.out.println("p:" + knot.getParent());
                //4 - (1 * 0) = 4
                //-2 - (-1 * -1) = -3

                //3 - 33
                //0 + 0 * 0 = 0
                knot.setX(draggingX - ((deltaX - 1) * direction.getXDelta()));
                knot.setY(draggingY - ((deltaY - 1) * direction.getYDelta()));

                System.out.printf("draggingX=%d, draggingY=%d, deltaX=%d, deltaY=%d, dirX=%d, dirY=%d",
                        draggingX, draggingY, deltaX, deltaY, direction.getXDelta(), direction.getYDelta());

                System.out.println();
                System.out.println("a2: " + knot);
            }
        } else {
            knot.setX(knot.x() + direction.getXDelta());
            knot.setY(knot.y() + direction.getYDelta());
        }
    }

    private void dragKnotBehind(Knot draggingKnot, Direction direction) {
        printSnake();
        printTail();
        System.out.println();
        if (draggingKnot == null) {
            return;
        }

        Knot previousKnot = new Knot(draggingKnot.x(), draggingKnot.y());

        updateKnot(draggingKnot, direction);

        Knot followingKnot = draggingKnot.getChild();

        if (followingKnot == null) {
            if (snake.size() < (tailLength + 1)) {
                followingKnot = previousKnot;
                followingKnot.setParent(draggingKnot);
                draggingKnot.setChild(followingKnot);
                snake.addLast(followingKnot);
            }
        } else {
            dragKnotBehind(followingKnot, direction);
        }
    }

    private void executeLine(Direction direction, int steps) {
        for (int i = steps; i > 0; i--) {
            dragKnotBehind(headKnot, direction);

            if (snake.size() > tailLength) {
                printSnake();
                visitedCoordinatesTail.add(new Knot(snake.getLast().x(), snake.getLast().y()));
                printTail();
            }
//            if (snake.size() > (tailLength + 1)) {
//                Knot lastTailNode = snake.removeLast();
//                System.out.println(snake.size());
//                System.out.println(String.format("Adding %s to the last tail visit stack", snake.peekLast()));
//                visitedCoordinatesTail.add(lastTailNode);
//            }

//            printStep();
        }
    }

    public Stack<Knot> getVisitedCoordinatesTail() {
        return visitedCoordinatesTail;
    }

    protected void printStep() {
        System.out.println("Tail:" + visitedCoordinatesTail.peek());
        System.out.println();
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
