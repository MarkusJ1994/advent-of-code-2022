package day10;

import java.util.ArrayList;
import java.util.List;

public class Device {

    private final List<String> input;
    private final int firstTap;
    private final int tapInterval;
    private final CRT crt = new CRT();
    private int cycle = 1;
    private int X = 1;
    private List<Integer> tappedSignals = new ArrayList<>();

    public Device(List<String> input, int firstTap, int tapInterval) {
        this.input = input;
        this.firstTap = firstTap;
        this.tapInterval = tapInterval;
    }

    private int calculateSignalStrength() {
        return cycle * X;
    }

    private void cycleCheck() {
        if (cycle == firstTap) {
            System.out.println(String.format("First (%dth) cycle has strength %d", cycle, calculateSignalStrength()));
//            System.out.println(String.format("%d * %d", cycle, X));
            tappedSignals.add(calculateSignalStrength());
        } else if ((cycle - firstTap) % tapInterval == 0) {
            System.out.println(String.format("(%dth) cycle has strength %d", cycle, calculateSignalStrength()));
//            System.out.println(String.format("%d * %d", cycle, X));
            tappedSignals.add(calculateSignalStrength());
        }
        crt.drawPixel(cycle, X);
//        System.out.println(String.format("%d * %d", cycle, X));
    }

    protected void readSignalStrength() {
        input.forEach(line -> {
            String[] instructions = line.split(" ");
            switch (instructions[0]) {
                case "noop" -> {
                    cycleCheck();
                    cycle++;
                }
                case "addx" -> {
                    cycleCheck();
                    cycle++;
                    cycleCheck();
                    cycle++;
                    X = X + Integer.parseInt(instructions[1]);
                }
            }
        });
    }

    protected int getTappedSignalsSum() {
        return tappedSignals.stream().mapToInt(val -> val).sum();
    }

    public CRT getCrt() {
        return crt;
    }
}
