package day10;

import java.util.Arrays;
import java.util.List;

public class CRT {

    private static final char lit = '#';
    private static final char dark = '.';
    private final int width = 40;
    private final int height = 6;
    private Character[][] crtScreen = new Character[height][width];

    private boolean isPixelVisible(int col, int X) {
        List<Integer> spritePositions = List.of(X - 1, X, X + 1);
        return spritePositions.contains(col);
    }

    protected void drawPixel(int cycle, int X) {
        int zeroIndexedCycle = cycle - 1;
        int row = (int) Math.floor((zeroIndexedCycle) / width);
        int col = zeroIndexedCycle - (row * width);

        System.out.println(String.format("[%d][%d] for cycle %d", row, col, cycle));

        if (isPixelVisible(col, X)) {
            crtScreen[row][col] = lit;
        } else {
            crtScreen[row][col] = dark;
        }
    }

    protected void printCRT() {
        Arrays.stream(crtScreen).forEach(row -> {
            String rRow = Arrays.stream(row).map(c -> c.toString()).reduce("", (val, acc) -> val + acc);
            System.out.println(rRow);
        });
    }

}
