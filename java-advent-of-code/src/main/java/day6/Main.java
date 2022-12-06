package day6;

import shared.FileReaderUtil;

public class Main {

    public static void main(String[] args) {

        String input = new FileReaderUtil().readFileAsString("day6_buffer.txt");

        part2(input);

    }

    private static void part1(String input) {
        ElfCommunicationDevice elfCommunicationDevice = new ElfCommunicationDevice(4);

        System.out.println("First marker found at: " + elfCommunicationDevice.findMarkerFromBuffer(input));
    }

    private static void part2(String input) {
        ElfCommunicationDevice elfCommunicationDevice = new ElfCommunicationDevice(14);

        System.out.println("First marker found at: " + elfCommunicationDevice.findMarkerFromBuffer(input));
    }

}
