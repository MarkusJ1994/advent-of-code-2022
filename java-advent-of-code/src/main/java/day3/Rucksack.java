package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Rucksack {

    private final String rucksack;

    public Rucksack(String rucksack) {
        this.rucksack = rucksack;
    }

    public String getFirstCompartment() {
        return rucksack.substring(0, rucksack.length() / 2);
    }

    public String getSecondCompartment() {
        return rucksack.substring(rucksack.length() / 2);
    }

    public Compartments getRucksackPriority() {
        Map<Character, Integer> characterPriorityMap = AlphabetPriority.alphabetToPriority();

        char[] firstCompartment = getFirstCompartment().toCharArray();
        char[] secondCompartment = getSecondCompartment().toCharArray();

        List<Integer> firstCompPriority = IntStream.range(0, firstCompartment.length).mapToObj(i -> firstCompartment[i]).map(c -> characterPriorityMap.get(c)).toList();
        List<Integer> secondCompPriority = IntStream.range(0, secondCompartment.length).mapToObj(i -> secondCompartment[i]).map(c -> characterPriorityMap.get(c)).toList();

        return new Compartments(firstCompPriority, secondCompPriority);
    }

    public Integer getMatchingItemsInRucksack() {
        Compartments rucksackPriority = getRucksackPriority();

        List<Integer> sharedItem = new ArrayList<>(rucksackPriority.firstCompartment);
        sharedItem.retainAll(rucksackPriority.secondCompartment);

        Integer sharedPriority = sharedItem.stream().findFirst().orElse(-1);

        return sharedPriority;
    }

    public record Compartments(List<Integer> firstCompartment, List<Integer> secondCompartment) {
    }

}
