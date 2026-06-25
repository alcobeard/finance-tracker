package selflearn.tasks;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class VowelsCounter {

    /**
     * Дана строка на английском языке. Нужно посчитать количество гласных букв. (a, e, i, o, u)
     * @param text - текст
     * @return - количество гласные
     */
    public static int countVowels(String text) {}

    public static void main(String[] args) {
        System.out.println(countVowels("hello") == 2);
        System.out.println(countVowels("JAVA") == 2);
        System.out.println(countVowels("sky") == 0);
        System.out.println(countVowels("") == 0);
        System.out.println(countVowels("Education") == 5);
    }
}
