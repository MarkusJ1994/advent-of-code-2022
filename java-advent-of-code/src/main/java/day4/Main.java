package day4;

import shared.FileReaderUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> input = new FileReaderUtil().readFile("cleaning_sections.txt");

        part2(input);
    }

    private static void part1(List<String> input) {
        List<SectionPair> sectionPairs = sectionPairsFullyContained(input);

        System.out.println("Number of pairs where one section is fully containted in the other");
        System.out.println(sectionPairs.size());
    }

    private static void part2(List<String> input) {
        List<SectionPair> sectionPairs = sectionPairsOverlap(input);

        System.out.println("Number of pairs where section overlap at all");
        System.out.println(sectionPairs.size());
    }

    protected static SectionPair rowToPair(String row) {
        String[] pair = row.split(",");
        String elf1 = pair[0];
        String elf2 = pair[1];
        return new SectionPair(parseSection(elf1), parseSection(elf2));
    }

    protected static List<SectionPair> sectionPairsFullyContained(List<String> input) {
        return input.stream()
                .map(row -> rowToPair(row))
                .filter(SectionPair::fullyContains)
                .toList();
    }

    protected static List<SectionPair> sectionPairsOverlap(List<String> input) {
        return input.stream().map(row -> {
            String[] pair = row.split(",");
            String elf1 = pair[0];
            String elf2 = pair[1];
            return new SectionPair(parseSection(elf1), parseSection(elf2));
        }).filter(SectionPair::overlap).toList();
    }

    private static SectionInterval parseSection(String section) {
        String[] split = section.split("-");
        int lowerBound = Integer.parseInt(split[0]);
        int upperBound = Integer.parseInt(split[1]);
        return new SectionInterval(lowerBound, upperBound);
    }

}
