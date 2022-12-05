package day5;

import java.util.Map;
import java.util.Stack;

public class Crane9000 extends Crane {

    public Crane9000(Map<Integer, Stack<Crate>> workLoad) {
        super(workLoad);
    }

    @Override
    public void move(int nrOfCrates, int from, int to) {
        for (int i = 0; i < nrOfCrates; i++) {
            Crate poppedCreate = workLoad.get(from).pop();
            workLoad.get(to).push(poppedCreate);
        }
    }

}
