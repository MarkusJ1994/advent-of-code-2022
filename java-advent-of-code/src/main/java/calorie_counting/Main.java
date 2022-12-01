package calorie_counting;

import shared.FileReaderUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> input = new FileReaderUtil().readFile("elf_calories.txt");

        Map<Integer, Integer> elfToCalories = new HashMap<>();
        int idx = 0;
        for (String row : input) {
            try {
                int parsedRow = Integer.parseInt(row);
                Integer currentCalories = elfToCalories.get(idx);
                elfToCalories.put(idx, currentCalories != null ? currentCalories + parsedRow : parsedRow);
            } catch (Exception e) {
                idx++;
            }
        }

        System.out.println("Maximum n calorie carriers");
        System.out.println(topNCaloriesTotal(elfToCalories, 3));
    }

    private static int topNCaloriesTotal(Map<Integer, Integer> elfToCalories, int n) {
        return elfToCalories.values().stream().sorted(Comparator.reverseOrder()).limit(n).reduce(0, (value, acc) -> acc + value);
    }
}
