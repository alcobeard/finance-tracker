package selflearn.tasks;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class EvenNumbers {
    /**
     * Дан массив целых чисел. Нужно посчитать, сколько в нем четных чисел.
     * @param numbers - массив чисел
     * @return int evenCount - количество четных чисел
     */

    public static int countEvenNumbers(int[] numbers) {
        int evenCount = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenCount = evenCount +1;
            }
        }
        return evenCount;
    }


    public static void main(String[] args) {
        System.out.println(countEvenNumbers(new int[]{1, 2, 3, 4}) == 2);
        System.out.println(countEvenNumbers(new int[]{2, 4, 6}) == 3);
        System.out.println(countEvenNumbers(new int[]{1, 3, 5}) == 0);
        System.out.println(countEvenNumbers(new int[]{0}) == 1);
    }
}
