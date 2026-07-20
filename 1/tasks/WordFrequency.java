package selflearn.tasks;

import java.util.List;
import java.util.Map;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class WordFrequency {

    /**
     * Дан список слов. Нужно вернуть Map, где ключ — это слово, а значение — сколько раз оно встретилось.
     * @param words - список слов
     * @return мапа с частотой
     */
    public static Map<String, Integer> countWords(List<String> words) {

    }

    public static void main(String[] args) {
        Map<String, Integer> result1 = countWords(List.of("java", "go", "java"));
        System.out.println(result1.get("java") == 2);
        System.out.println(result1.get("go") == 1);

        Map<String, Integer> result2 = countWords(List.of("a", "b", "a", "c", "b", "a"));
        System.out.println(result2.get("a") == 3);
        System.out.println(result2.get("b") == 2);
        System.out.println(result2.get("c") == 1);

        Map<String, Integer> result3 = countWords(List.of());
        System.out.println(result3.isEmpty());
    }
}
