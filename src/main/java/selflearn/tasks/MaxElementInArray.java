package selflearn.tasks;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class MaxElementInArray {
    /**
     * Дан массив целых чисел. Нужно вернуть максимальный элемент.
     *
     * Примеры:
     * findMax(new int[]{1, 5, 3}) -> 5
     * findMax(new int[]{-10, -3, -7}) -> -3
     *
     * @param numbers - массив элементов
     * @return int maxElement - максимальный элемент
     */
    public static int findMax(int[] numbers) {}

    public static void main(String[] args) {
        System.out.println(findMax(new int[]{1, 5, 3}) == 5);
        System.out.println(findMax(new int[]{-10, -3, -7}) == -3);
        System.out.println(findMax(new int[]{100}) == 100);
        System.out.println(findMax(new int[]{4, 4, 4}) == 4);
    }
}
