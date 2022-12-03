package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RucksackGroup {

    private List<Rucksack> rucksacks;

    public RucksackGroup(List<Rucksack> rucksacks) {
        this.rucksacks = rucksacks;
    }

    public Integer getMatchingItemsInGroup() {
        List<List<Integer>> rucksackPriorities = rucksacks.stream().map(rs -> Stream.concat(rs.getRucksackPriority().firstCompartment().stream(), rs.getRucksackPriority().secondCompartment().stream()).toList()).toList();

        List<Integer> sharedItemPriority = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                sharedItemPriority.addAll(rucksackPriorities.get(i));
            } else {
                sharedItemPriority.retainAll(rucksackPriorities.get(i));
            }
        }

        return sharedItemPriority.stream().findFirst().orElse(-1);
    }

}
