package day11;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {

    protected static Map<Integer, Monkey> initMonkeys() {
        Monkey monkey0 = new Monkey(0, (List.of(63, 84, 80, 83, 84, 53, 88, 72)), val -> val * 11, 13, 4, 7);

        Monkey monkey1 = new Monkey(1, (List.of(67, 56, 92, 88, 84)), val -> val + 4, 11, 5, 3);

        Monkey monkey2 = new Monkey(2, (List.of(52)), val -> val * val, 2, 3, 1);

        Monkey monkey3 = new Monkey(3, (List.of(59, 53, 60, 92, 69, 72)), val -> val + 2, 5, 5, 6);

        Monkey monkey4 = new Monkey(4, (List.of(61, 52, 55, 61)), val -> val + 3, 7, 7, 2);

        Monkey monkey5 = new Monkey(5, (List.of(79, 53)), val -> val + 1, 3, 0, 6);

        Monkey monkey6 = new Monkey(6, (List.of(59, 86, 67, 95, 92, 77, 91)), val -> val + 5, 19, 4, 0);

        Monkey monkey7 = new Monkey(7, (List.of(58, 83, 89)), val -> val * 19, 17, 2, 1);

        return Map.of(
                monkey0.getIndex(), monkey0,
                monkey1.getIndex(), monkey1,
                monkey2.getIndex(), monkey2,
                monkey3.getIndex(), monkey3,
                monkey4.getIndex(), monkey4,
                monkey5.getIndex(), monkey5,
                monkey6.getIndex(), monkey6,
                monkey7.getIndex(), monkey7);
    }

    public static void main(String[] args) {
        part2();
    }

    private static void part1() {
        Map<Integer, Monkey> monkeys = initMonkeys();
        System.out.println("Monkey business is " + performRounds(20, monkeys, 1, 3));
    }

    private static void part2() {

        Map<Integer, Monkey> monkeys = initMonkeys();

        Integer dividerLcm = monkeys.values().stream().map(Monkey::getDivider).reduce(1, (val, tot) -> tot * val);

        System.out.println("Monkey business is " + performRounds(10000, monkeys, dividerLcm, 1));
    }

    protected static long performRounds(int rounds, Map<Integer, Monkey> monkeys, int lcmMod, int divisor) {
        for (int i = 0; i < rounds; i++) {
            for (int idx = 0; idx < monkeys.size(); idx++) {
                monkeys.get(idx).performRound(monkeys, lcmMod, divisor);
            }
        }

        List<Integer> inspections = monkeys.values().stream().map(Monkey::getInspectCount).sorted(Comparator.reverseOrder()).toList();

        long val1 = inspections.get(0);
        long val2 = inspections.get(1);

        return val1 * val2;
    }

    protected static int findLCM(List<Integer> values) {
        int lcm = 0;
        for (int i = 0; i < values.size(); i++) {
            if (i == 0) {
                lcm = values.get(0);
            } else {
                lcm = lcm(lcm, values.get(i));
            }
        }
        return lcm;
    }

    protected static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        int absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }
}
