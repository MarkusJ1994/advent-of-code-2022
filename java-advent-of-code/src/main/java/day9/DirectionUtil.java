package day9;

public class DirectionUtil {

    public static Direction fromString(String dir) {
        return switch (dir) {
            case "U" -> Direction.UP;
            case "D" -> Direction.DOWN;
            case "L" -> Direction.LEFT;
            case "R" -> Direction.RIGHT;
            default -> throw new IllegalArgumentException("Unknown direction");
        };
    }

    public static Direction reverse(Direction dir) {
        return switch (dir) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        };
    }

    //Up/Down are opposite here due to how they are visualized in the challenge
    public enum Direction {
        UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0);

        private final int xDelta, yDelta;

        Direction(int xDelta, int yDelta) {
            this.xDelta = xDelta;
            this.yDelta = yDelta;
        }

        public int getXDelta() {
            return xDelta;
        }

        public int getYDelta() {
            return yDelta;
        }
    }

}
