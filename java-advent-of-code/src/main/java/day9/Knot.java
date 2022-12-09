package day9;

import java.util.Objects;

public class Knot {

    private int x, y;

    private Knot parent;
    private Knot child;

    public Knot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int y() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int calculateYDistance(Knot other) {
        return Math.abs(y - other.y);
    }

    public int calculateXDistance(Knot other) {
        return Math.abs(x - other.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knot that = (Knot) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[x=" +
                x +
                ", y=" + y +
                ']';
    }

    public Knot getChild() {
        return child;
    }

    public void setChild(Knot child) {
        this.child = child;
    }

    public Knot getParent() {
        return parent;
    }

    public void setParent(Knot parent) {
        this.parent = parent;
    }
}
