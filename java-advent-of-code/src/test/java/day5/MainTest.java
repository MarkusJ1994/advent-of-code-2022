package day5;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    String testData = "    [D]     \n" +
            "[N] [C]     \n" +
            "[Z] [M] [P] \n" +
            " 1   2   3  \n" +
            "\n" +
            "move 1 from 2 to 1\n" +
            "move 3 from 1 to 3\n" +
            "move 2 from 2 to 1\n" +
            "move 1 from 1 to 2";

    List<String> testStackInput = List.of(
            "                    [Q]     [P] [P] ",
            "                [G] [V] [S] [Z] [F] ",
            "            [W] [V] [F] [Z] [W] [Q] ",
            "        [V] [T] [N] [J] [W] [B] [W] ",
            "    [Z] [L] [V] [B] [C] [R] [N] [M] ",
            "[C] [W] [R] [H] [H] [P] [T] [M] [B] ",
            "[Q] [Q] [M] [Z] [Z] [N] [G] [G] [J] ",
            "[B] [R] [B] [C] [D] [H] [D] [C] [N] ",
            " 1   2   3   4   5   6   7   8   9  "
    );

    @Test
    public void testDerivationOfStackSize() {
        assertEquals(9, Main.parseStackSetup(testStackInput).size());
    }

    @Test
    public void testParsingRow() {
        Map<Integer, Stack<Crate>> map = Main.initializeStacks(9);
        Main.parseRow(map, "                    [Q]     [P] [P] ");
        assertEquals("Q", map.get(6).pop().content());
        assertEquals("P", map.get(8).pop().content());
        assertEquals("P", map.get(9).pop().content());
    }

    @Test
    public void testOperations() {
        Map<Integer, Stack<Crate>> indexedStackMap = new HashMap<>();

        indexedStackMap.put(1, new Stack<>());
        indexedStackMap.get(1).push(new Crate("A"));

        indexedStackMap.put(2, new Stack<>());
        indexedStackMap.get(2).push(new Crate("B"));
        indexedStackMap.get(2).push(new Crate("C"));
        indexedStackMap.get(2).push(new Crate("D"));

        indexedStackMap.put(3, new Stack<>());
        indexedStackMap.get(3).push(new Crate("E"));
        indexedStackMap.get(3).push(new Crate("A"));


        Crane9000 crane = new Crane9000(indexedStackMap);

        //Move 1 crate from stack 1 to stack 2
        crane.parseInstruction(operation(1, 1, 2));

        assertEquals(0, indexedStackMap.get(1).size());
        assertEquals(4, indexedStackMap.get(2).size());
        assertEquals("A", indexedStackMap.get(2).peek().content());

        //Move 3 crate from stack 2 to stack 1
        crane.parseInstruction(operation(3, 2, 1));

        assertEquals(3, indexedStackMap.get(1).size());
        assertEquals(1, indexedStackMap.get(2).size());
        assertEquals("C", indexedStackMap.get(1).peek().content());
    }

    @Test
    public void smallTestPart1() {
        assertEquals("CMZ", Main.part1(testData).getTopCratesOfEachStack());
    }

    @Test
    public void smallTestPart2() {
        assertEquals("MCD", Main.part2(testData).getTopCratesOfEachStack());
    }

    private String operation(int stacks, int from, int to) {
        return String.format("move %d from %d to %d", stacks, from, to);
    }

}