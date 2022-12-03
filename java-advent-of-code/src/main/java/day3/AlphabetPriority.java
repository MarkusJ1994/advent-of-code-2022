package day3;

import java.util.HashMap;
import java.util.Map;

public class AlphabetPriority {

    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private static char[] alphabetLowerCase = alphabet.toCharArray();
    private static char[] alphabetUpperCase = alphabet.toUpperCase().toCharArray();

    public static Map<Character, Integer> alphabetToPriority() {

        Map<Character, Integer> characterPriorityHashMap = new HashMap<>();

        int priority;
        for (char c : alphabetLowerCase) {
            priority = c;
            characterPriorityHashMap.put(c, priority - 96);
        }

        for (char c : alphabetUpperCase) {
            priority = c;
            characterPriorityHashMap.put(c, priority - 38);
        }

        return characterPriorityHashMap;
    }

    public static Map<Integer, Character> priorityToAlphabet() {

        Map<Integer, Character> characterPriorityHashMap = new HashMap<>();

        int priority;
        for (char c : alphabetLowerCase) {
            priority = c;
            characterPriorityHashMap.put(priority - 96, c);
        }

        for (char c : alphabetUpperCase) {
            priority = c;
            characterPriorityHashMap.put(priority - 38, c);
        }

        return characterPriorityHashMap;
    }

}
