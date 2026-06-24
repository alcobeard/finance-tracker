package selflearn.tasks;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class SumArray {
    /**
     * Дан массив целых чисел. Нужно вернуть сумму всех элементов массива.
     *
     * Пример:
     * sumArray(new int[]{1, 2, 3}) -> 6
     * sumArray(new int[]{-1, 5, 10}) -> 14
     * sumArray(new int[]{}) -> 0
     *
     * @param numbers - массив чисел
     * @return int интовое значение суммы элементов
     */
    public static int sumArray(int[] numbers) {}

    public static void main(String[] args) {
        System.out.println(sumArray(new int[]{1, 2, 3}) == 6);
        System.out.println(sumArray(new int[]{-1, 5, 10}) == 14);
        System.out.println(sumArray(new int[]{0, 0, 0}) == 0);
        System.out.println(sumArray(new int[]{}) == 0);
    }
}
