package day3;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static day3.AlphabetPriority.alphabetToPriority;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    public void parseAlphabetAscii() {

        Map<Character, Integer> characterIntegerMap = alphabetToPriority();

        assertEquals(1, characterIntegerMap.get('a'));
        assertEquals(11, characterIntegerMap.get('k'));
        assertEquals(26, characterIntegerMap.get('z'));
        assertEquals(27, characterIntegerMap.get('A'));
        assertEquals(42, characterIntegerMap.get('P'));
        assertEquals(52, characterIntegerMap.get('Z'));

    }

}