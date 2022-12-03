package day3;

import shared.FileReaderUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        part2();
    }

    private static void part1() {
        List<String> inputRucksacks = new FileReaderUtil().readFile("package_compartments.txt");

        int sum = inputRucksacks.stream().mapToInt(s -> new Rucksack(s).getMatchingItemsInRucksack()).sum();

        System.out.println("Sum of all matching items");
        System.out.println(sum);
    }

    private static void part2() {
        List<String> inputRucksacks = new FileReaderUtil().readFile("package_compartments.txt");

        List<Rucksack> rucksacks = inputRucksacks.stream().map(s -> new Rucksack(s)).toList();

        List<RucksackGroup> rucksackGroups = new ArrayList<>();

        for (int i = 0; i < rucksacks.size(); i += 3) {
            rucksackGroups.add(new RucksackGroup(rucksacks.subList(i, Math.min(i + 3, rucksacks.size()))));
        }

        int sum = rucksackGroups.stream().mapToInt(rg -> rg.getMatchingItemsInGroup()).sum();

        System.out.println("Sum of all matching items in group");
        System.out.println(sum);
    }

}
