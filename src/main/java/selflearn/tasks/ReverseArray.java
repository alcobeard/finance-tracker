package selflearn.tasks;

import java.util.Arrays;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class ReverseArray {
    /**
     * Дан массив целых чисел. Нужно вернуть новый массив, в котором элементы идут в обратном порядке.
     *
     * Пример:
     * reverseArray(new int[]{1, 2, 3}) -> {3, 2, 1}
     * reverseArray(new int[]{5}) -> {5}
     *
     * @param numbers - массив чисел
     * @return массив чисел в обратном порядке
     */
    public static int[] reverseArray(int[] numbers) {

    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(reverseArray(new int[]{1, 2, 3}), new int[]{3, 2, 1}));
        System.out.println(Arrays.equals(reverseArray(new int[]{5}), new int[]{5}));
        System.out.println(Arrays.equals(reverseArray(new int[]{}), new int[]{}));
        System.out.println(Arrays.equals(reverseArray(new int[]{1, 2, 3, 4}), new int[]{4, 3, 2, 1}));
    }
}
