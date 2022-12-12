package day11;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

public class Monkey {

    private final int index;
    private final Function<Long, Long> operation;
    private final int divider;
    private final int trueIndex;
    private final int falseIndex;
    private final Queue<Long> items;
    private int inspectCount = 0;

    public Monkey(int index, List<Integer> items, Function<Long, Long> operation, int divider, int trueIndex, int falseIndex) {
        this.index = index;
        this.items = new ArrayDeque<>(items.stream().map(Long::valueOf).toList());
        this.operation = operation;
        this.divider = divider;
        this.trueIndex = trueIndex;
        this.falseIndex = falseIndex;
    }

    protected Long mod(Long item) {
        return item % divider;
    }

    protected void performRound(Map<Integer, Monkey> monkeys, int lcmMod, int boredomDivisor) {
        //Grab item to inspect
        while (items.peek() != null) {
            Long itemToInspect = items.poll();
            //Apply modifier operation and boredom divider
            long processedItem = operation.apply(itemToInspect) / boredomDivisor;
            if (lcmMod > 0) {
                processedItem = processedItem % lcmMod;
            }
            //Test worry level
            if (mod(processedItem) == 0) {
                //Throw to true result monkey
                monkeys.get(trueIndex).throwItem(processedItem);
            } else {
                //Throw to false result monkey
                monkeys.get(falseIndex).throwItem(processedItem);
            }
            inspectCount++;
        }
    }

    protected void throwItem(Long item) {
        items.add(item);
    }

    protected int getIndex() {
        return index;
    }

    protected int getDivider() {
        return divider;
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
