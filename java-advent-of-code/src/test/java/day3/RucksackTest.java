package day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RucksackTest {

    @Test
    public void testRucksackPriorityParsingEx1() {
        String inputRucksack = "vJrwpWtwJgWrhcsFMMfFFhFp";

        Rucksack rucksack = new Rucksack(inputRucksack);

        assertEquals("vJrwpWtwJgWr", rucksack.getFirstCompartment());
        assertEquals("hcsFMMfFFhFp", rucksack.getSecondCompartment());

        Integer matchingItemsInRucksack = rucksack.getMatchingItemsInRucksack();

        assertEquals(AlphabetPriority.alphabetToPriority().get('p'), matchingItemsInRucksack);
    }

    @Test
    public void testRucksackPriorityParsingEx2() {
        String inputRucksack = "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL";

        Rucksack rucksack = new Rucksack(inputRucksack);

        assertEquals("jqHRNqRjqzjGDLGL", rucksack.getFirstCompartment());
        assertEquals("rsFMfFZSrLrFZsSL", rucksack.getSecondCompartment());

        Integer matchingItemsInRucksack = rucksack.getMatchingItemsInRucksack();

        assertEquals(AlphabetPriority.alphabetToPriority().get('L'), matchingItemsInRucksack);
    }

}