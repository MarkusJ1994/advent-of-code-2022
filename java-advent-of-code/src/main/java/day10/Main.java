package day10;

import shared.FileReaderUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> input = new FileReaderUtil().readFile("day10_signal_input.txt");
        part2(input);
    }

    private static void part1(List<String> input) {
        Device device = new Device(input, 20, 40);
        device.readSignalStrength();
        System.out.println(String.format("Sum of tapped signals is %d", device.getTappedSignalsSum()));
    }

    private static void part2(List<String> input) {
        Device device = new Device(input, 20, 40);
        device.readSignalStrength();
        device.getCrt().printCRT();
    }

}
