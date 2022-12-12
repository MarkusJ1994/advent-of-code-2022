package day11;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private static Map<Integer, Monkey> initTestMonkeys() {
        Monkey monkey0 = new Monkey(0, new ArrayList<>(List.of(79, 98)), val -> val * 19, 23, 2, 3);

        Monkey monkey1 = new Monkey(1, new ArrayList<>(List.of(54, 65, 75, 74)), val -> val + 6, 19, 2, 0);

        Monkey monkey2 = new Monkey(2, new ArrayList<>(List.of(79, 60, 97)), val -> val * val, 13, 1, 3);

        Monkey monkey3 = new Monkey(3, new ArrayList<>(List.of(74)), val -> val + 3, 17, 0, 1);

        return Map.of(
                monkey0.getIndex(), monkey0,
                monkey1.getIndex(), monkey1,
                monkey2.getIndex(), monkey2,
                monkey3.getIndex(), monkey3);
    }

    @Test
    public void testMonkeyBsnsP1() {
        Map<Integer, Monkey> integerMonkeyMap = initTestMonkeys();

        assertEquals(10605, Main.performRounds(20, integerMonkeyMap, 0, 3));
    }

    @Test
    public void testMonkeyBsnsP2() {
        Map<Integer, Monkey> integerMonkeyMap = initTestMonkeys();

        assertEquals(2713310158L, Main.performRounds(10000, integerMonkeyMap, 96577, 1));
    }

    @Test
    public void testLCM() {
        int lcm = Main.findLCM(List.of(6, 12, 18));
        assertEquals(36, lcm);

        Map<Integer, Monkey> monkeys = initTestMonkeys();

        int lcm2 = Main.findLCM(monkeys.values().stream().map(Monkey::getDivider).toList());

        System.out.println(lcm2);
    }

}