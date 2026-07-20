package selflearn.tasks;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class SumHigher {
    /**
     * Дан массив целых чисел и число limit.
     * Нужно вернуть сумму только тех элементов массива, которые строго больше limit.
     * @param numbers - массив чисел
     * @param limit - лимит
     * @return - сумма
     */
    public static int sumGreaterThan(int[] numbers, int limit) {

    }

    public static void main(String[] args) {
        System.out.println(sumGreaterThan(new int[]{1, 5, 10, 2}, 3) == 15);
        System.out.println(sumGreaterThan(new int[]{1, 2, 3}, 5) == 0);
        System.out.println(sumGreaterThan(new int[]{-1, 0, 4}, 0) == 4);
        System.out.println(sumGreaterThan(new int[]{}, 10) == 0);
    }
}
