package day5;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class Crane {

    protected Map<Integer, Stack<Crate>> workLoad;

    public Crane(Map<Integer, Stack<Crate>> workLoad) {
        this.workLoad = workLoad;
    }

    public void parseInstruction(String instructionLine) {
        String[] s = instructionLine.split(" ");

        int nrOfCrates = Integer.parseInt(s[1]);

        int fromStack = Integer.parseInt(s[3]);

        int toStack = Integer.parseInt(s[5]);

        move(nrOfCrates, fromStack, toStack);
    }

    public abstract void move(int nrOfCrates, int from, int to);

    public Map<Integer, Stack<Crate>> getWorkLoad() {
        return workLoad;
    }

    public void printWorkload() {
        workLoad.keySet().forEach(key -> System.out.println(String.format("%d: %s", key, workLoad.get(key))));
    }

    public String getTopCratesOfEachStack() {
        List<String> topValues = new java.util.ArrayList<>(workLoad.values().stream().map(stack -> stack.peek().content()).toList());
        Collections.reverse(topValues);
        return topValues.stream().reduce("", (s, acc) -> acc + s);
    }

}
