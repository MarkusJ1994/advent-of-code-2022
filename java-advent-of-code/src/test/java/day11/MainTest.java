package day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MainTest {

    private static Map<Integer, Monkey> initTestMonkeys() {
        Monkey monkey0 = new Monkey(0, new ArrayList<>(List.of(79, 98)), val -> val * 19, val -> val % 23 == 0, 2, 3);

        Monkey monkey1 = new Monkey(1, new ArrayList<>(List.of(54, 65, 75, 74)), val -> val + 6, val -> val % 19 == 0, 2, 0);

        Monkey monkey2 = new Monkey(2, new ArrayList<>(List.of(79, 60, 97)), val -> val * val, val -> val % 13 == 0, 1, 3);

        Monkey monkey3 = new Monkey(3, new ArrayList<>(List.of(74)), val -> val + 3, val -> val % 17 == 0, 0, 1);

        return Map.of(
                monkey0.getIndex(), monkey0,
                monkey1.getIndex(), monkey1,
                monkey2.getIndex(), monkey2,
                monkey3.getIndex(), monkey3);
    }

    @Test
    public void testMonkeyBsns() {
        Map<Integer, Monkey> integerMonkeyMap = initTestMonkeys();
        
        Assertions.assertEquals(10605, Main.performRounds(20, integerMonkeyMap));
    }

}