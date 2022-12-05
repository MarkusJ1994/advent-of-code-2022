package day5;

import java.util.Map;
import java.util.Stack;

public class Crane9001 extends Crane {

    public Crane9001(Map<Integer, Stack<Crate>> workLoad) {
        super(workLoad);
    }

    @Override
    public void move(int nrOfCrates, int from, int to) {
        Stack<Crate> tmpStack = new Stack();
        for (int i = 0; i < nrOfCrates; i++) {
            Crate poppedCreate = workLoad.get(from).pop();
            tmpStack.push(poppedCreate);
        }

        for (int i = 0; i < nrOfCrates; i++) {
            Crate poppedCreate = tmpStack.pop();
            workLoad.get(to).push(poppedCreate);
        }
    }
}
