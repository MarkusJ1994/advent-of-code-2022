package day11;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {

    private static Map<Integer, Monkey> initMonkeys() {
        Monkey monkey0 = new Monkey(0, (List.of(63, 84, 80, 83, 84, 53, 88, 72)), val -> val * 11, val -> val % 13 == 0, 4, 7);

        Monkey monkey1 = new Monkey(1, (List.of(67, 56, 92, 88, 84)), val -> val + 4, val -> val % 11 == 0, 5, 3);

        Monkey monkey2 = new Monkey(2, (List.of(52)), val -> val * val, val -> val % 2 == 0, 3, 1);

        Monkey monkey3 = new Monkey(3, (List.of(59, 53, 60, 92, 69, 72)), val -> val + 2, val -> val % 5 == 0, 5, 6);

        Monkey monkey4 = new Monkey(4, (List.of(61, 52, 55, 61)), val -> val + 3, val -> val % 7 == 0, 7, 2);

        Monkey monkey5 = new Monkey(5, (List.of(79, 53)), val -> val + 1, val -> val % 3 == 0, 0, 6);

        Monkey monkey6 = new Monkey(6, (List.of(59, 86, 67, 95, 92, 77, 91)), val -> val + 5, val -> val % 19 == 0, 4, 0);

        Monkey monkey7 = new Monkey(7, (List.of(58, 83, 89)), val -> val * 19, val -> val % 17 == 0, 2, 1);

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
        Map<Integer, Monkey> monkeys = initMonkeys();
        part1(monkeys);
    }

    private static void part1(Map<Integer, Monkey> monkeys) {
        System.out.println("Monkey business is " + performRounds(20, monkeys));
    }

    private static void part2(Map<Integer, Monkey> monkeys) {
        System.out.println("Monkey business is " + performRounds(20, monkeys));
    }

    protected static int performRounds(int rounds, Map<Integer, Monkey> monkeys) {
        for (int i = 0; i < rounds; i++) {
            for (int idx = 0; idx < monkeys.size(); idx++) {
                monkeys.get(idx).performRound(monkeys);
            }
            final int round = i;
            monkeys.values().forEach(monkey -> System.out.println(round + ": " + monkey));
        }

        List<Integer> inspections = monkeys.values().stream().map(monkey -> monkey.getInspectCount()).sorted(Comparator.reverseOrder()).toList();

        return inspections.get(0) * inspections.get(1);
    }

}
