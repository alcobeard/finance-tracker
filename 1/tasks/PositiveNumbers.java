package selflearn.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class PositiveNumbers {
    /**
     * Дан список целых чисел. Нужно вернуть новый список, в котором останутся только положительные числа.
     * @param numbers - список целых чисел
     * @return - новый список, в котором останутся только положительные числа
     *
     * Примеры:
     * getPositiveNumbers(List.of(-1, 2, 0, 5)) -> [2, 5]
     * getPositiveNumbers(List.of(-3, -2, -1)) -> []
     */
    public static List<Integer> getPositiveNumbers(List<Integer> numbers) {
        List <Integer> positiveNum = new ArrayList<>();
        for (int num : numbers) {
            if (num > 0) {
                positiveNum.add(num);
            }
        }
        return positiveNum;
    }

    public static void main(String[] args) {
        System.out.println(getPositiveNumbers(List.of(-1, 2, 0, 5)).equals(List.of(2, 5)));
        System.out.println(getPositiveNumbers(List.of(-3, -2, -1)).equals(List.of()));
        System.out.println(getPositiveNumbers(List.of(1, 2, 3)).equals(List.of(1, 2, 3)));
        System.out.println(getPositiveNumbers(List.of()).equals(List.of()));
    }
}
