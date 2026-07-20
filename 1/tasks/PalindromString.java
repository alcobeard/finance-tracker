package selflearn.tasks;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class PalindromString {
    /**
     * Дана строка. Нужно проверить, читается ли она одинаково слева направо и справа налево.
     * Регистр учитывать нужно.
     * Пробелы тоже учитывать нужно.
     * @param text - входная строка
     * @return - true / false
     *
     * Примеры:
     * isPalindrome("level") -> true
     * isPalindrome("java") -> false
     * isPalindrome("aa") -> true
     */
    public static boolean isPalindrome(String text) {
        for (int i = 0; i < text.length() / 2; i++) {
            if (text.charAt(i) != text.charAt(text.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("level") == true);
        System.out.println(isPalindrome("java") == false);
        System.out.println(isPalindrome("a") == true);
        System.out.println(isPalindrome("") == true);
        System.out.println(isPalindrome("abBA") == false);
    }
}
