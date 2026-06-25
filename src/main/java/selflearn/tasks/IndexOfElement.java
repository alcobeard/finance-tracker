package selflearn.tasks;

import java.util.List;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class IndexOfElement {
    /**
     * Нужно вернуть индекс первого найденного элемента.
     * Если элемент не найден, вернуть -1.
     *
     * @param items - список строк
     * @param target - строка, которую нужно найти.
     * @return - индекс первого найденного элемента
     *
     * Примеры:
     * findIndex(List.of("java", "go", "python"), "go") -> 1
     * findIndex(List.of("java", "go"), "kotlin") -> -1
     */
    public static int findIndex(List<String> items, String target) {

    }

    public static void main(String[] args) {
        System.out.println(findIndex(List.of("java", "go", "python"), "go") == 1);
        System.out.println(findIndex(List.of("java", "go"), "kotlin") == -1);
        System.out.println(findIndex(List.of("a", "b", "a"), "a") == 0);
        System.out.println(findIndex(List.of(), "java") == -1);
    }
}
