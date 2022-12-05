package day5;

import shared.FileReaderUtil;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        part2(new FileReaderUtil().readFileAsString("stack_reordering.txt"));
    }

    protected static Crane9001 part2(String fileInput) {
        List<List<String>> input = splitStacksFromOperations(fileInput);

        Map<Integer, Stack<Crate>> indexedStacks = parseStackSetup(input.get(0));

        indexedStacks.values().forEach(System.out::println);

        Crane9001 crane = new Crane9001(indexedStacks);

        for (String operation : input.get(1)) {
            crane.parseInstruction(operation);
        }

        System.out.println();

        crane.getWorkLoad().values().forEach(System.out::println);

        System.out.println(crane.getTopCratesOfEachStack());

        return crane;
    }

    protected static Crane9000 part1(String fileInput) {
        List<List<String>> input = splitStacksFromOperations(fileInput);

        Map<Integer, Stack<Crate>> indexedStacks = parseStackSetup(input.get(0));

        indexedStacks.values().forEach(System.out::println);

        Crane9000 crane = new Crane9000(indexedStacks);

        for (String operation : input.get(1)) {
            crane.parseInstruction(operation);
        }

        System.out.println();

        crane.getWorkLoad().values().forEach(System.out::println);

        System.out.println(crane.getTopCratesOfEachStack());

        return crane;
    }

    protected static List<List<String>> splitStacksFromOperations(String input) {
        return Arrays.stream(
                        input.split("\\n\\n"))
                .map(s -> Arrays.stream(s.split("\n")).toList()).toList();
    }

    protected static Map<Integer, Stack<Crate>> parseStackSetup(List<String> input) {

        // always formatted as [X]<space> => 4 characters
        Map<Integer, Stack<Crate>> stacks = initializeStacks(input.get(0).length() / 4);
        for (String row : input) {
            if (row.trim().charAt(0) != '[') {
                break;
            }
            parseRow(stacks, row);
        }

        //Stacks are built in the wrong order since we read input top down. Need to reverse the stacks
        for (Integer key : stacks.keySet()) {
            Stack<Crate> value = stacks.get(key);
            Stack reverseStack = new Stack();
            while (!value.empty()) {
                reverseStack.push(value.pop());
            }
            stacks.put(key, reverseStack);
        }

        return stacks;
    }

    protected static Map<Integer, Stack<Crate>> initializeStacks(int nrOfStacks) {
        Map<Integer, Stack<Crate>> stacks = new HashMap<>();
        for (int i = 0; i < nrOfStacks; i++) {
            stacks.put(i + 1, new Stack());
        }
        return stacks;
    }

    protected static void parseRow(Map<Integer, Stack<Crate>> stacks, String row) {
        int nrOfStacks = stacks.size();

        for (int i = 0; i < nrOfStacks; i++) {
            //[X]<space> => X
            String crateRep = row.substring(i * 4, i * 4 + 4).trim();
            if (!crateRep.isBlank()) {
                stacks.get(i + 1).add(new Crate(crateRep.substring(1, 2)));
            }
        }
    }

}
