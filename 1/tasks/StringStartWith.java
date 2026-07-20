package selflearn.tasks;

import java.util.List;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class StringStartWith {
    /**
     * Дан список строк и символ letter.
     * Нужно вернуть новый список строк, которые начинаются с этой буквы.
     * Регистр учитывать нужно.
     * @param words - список слов
     * @param letter - буква, с которой должно начинаться слово
     * @return - отфильтрованный список
     */
    public static List<String> filterByFirstLetter(List<String> words, char letter) {

    }

    public static void main(String[] args) {
        System.out.println(filterByFirstLetter(List.of("apple", "banana", "apricot"), 'a')
                .equals(List.of("apple", "apricot")));

        System.out.println(filterByFirstLetter(List.of("Java", "java"), 'j')
                .equals(List.of("java")));

        System.out.println(filterByFirstLetter(List.of("cat", "dog"), 'b')
                .equals(List.of()));

        System.out.println(filterByFirstLetter(List.of(), 'a')
                .equals(List.of()));
    }
}

