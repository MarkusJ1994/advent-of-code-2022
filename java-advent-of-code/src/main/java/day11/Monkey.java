package day11;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;

public class Monkey {

    private final int index;
    private final Function<Integer, Integer> operation;
    private final Predicate<Integer> test;
    private final int trueIndex;
    private final int falseIndex;
    private final int boredomDivider = 3;
    private Queue<Integer> items;
    private int inspectCount = 0;

    public Monkey(int index, List<Integer> items, Function<Integer, Integer> operation, Predicate<Integer> test, int trueIndex, int falseIndex) {
        this.index = index;
        this.items = new ArrayDeque<>(items);
        this.operation = operation;
        this.test = test;
        this.trueIndex = trueIndex;
        this.falseIndex = falseIndex;
    }

    public int getIndex() {
        return index;
    }

    protected void performRound(Map<Integer, Monkey> monkeys) {
        //Grab item to inspect
        while (items.peek() != null) {
            int itemToInspect = items.poll();
            System.out.println(String.format("Performing round for monkey %d and item %d", index, itemToInspect));
            //Apply modifier operation and boredom divider
            int processedItem = operation.apply(itemToInspect) / boredomDivider;
            //Test worry level
            if (test.test(processedItem)) {
                //Throw to true result monkey
                monkeys.get(trueIndex).throwItem(processedItem);
            } else {
                //Throw to false result monkey
                monkeys.get(falseIndex).throwItem(processedItem);
            }
            inspectCount++;
        }
    }

    protected void throwItem(Integer item) {
        items.add(item);
    }

    protected int getInspectCount() {
        return inspectCount;
    }

    @Override
    public String toString() {
        return "Monkey[" + index + "]{" +
                "inspectCount=" + inspectCount +
                ", items=" + items +
                '}';
    }
}
