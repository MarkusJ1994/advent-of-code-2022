package calorie_counting;

import shared.FileReaderUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = new FileReaderUtil().readFileAsString("elf_calories.txt");

        List<Integer> caloriesPerElf = Arrays.stream(input.split("\n\n"))
                .map(elfCalories ->
                        Arrays.stream(elfCalories.split("\n"))
                                .map(Integer::parseInt)
                                .reduce(0, (val, tot) -> tot + val))
                .toList();

        System.out.println("Maximum n calorie carriers");
        System.out.println(topNCaloriesTotal(caloriesPerElf, 3));
    }

    private static int topNCaloriesTotal(List<Integer> caloriesPerElf, int n) {
        return caloriesPerElf.stream().sorted(Comparator.reverseOrder()).limit(n).reduce(0, (value, acc) -> acc + value);
    }
}
