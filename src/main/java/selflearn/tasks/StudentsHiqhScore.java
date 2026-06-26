package selflearn.tasks;

import java.util.List;
import java.util.Map;

/**
 * @author Aleksandr Bagdasarov
 * 2026-06
 */

public class StudentsHiqhScore {
    /**
     * Дана мапа, где ключ — имя студента, а значение — его оценка.
     * Нужно вернуть список имен студентов, у которых оценка больше или равна минимальной оценке.
     * Порядок студентов в результате не важен.
     * @param grades - мапа {имя: оценка}
     * @param minGrade - минимальная оценка
     * @return - список имен
     */
    public static List<String> findStudentsByMinGrade(Map<String, Integer> grades, int minGrade) {

    }

    public static void main(String[] args) {
        Map<String, Integer> grades1 = Map.of(
                "Alex", 5,
                "Ivan", 3,
                "Maria", 4
        );

        List<String> result1 = findStudentsByMinGrade(grades1, 4);
        System.out.println(result1.contains("Alex"));
        System.out.println(result1.contains("Maria"));
        System.out.println(!result1.contains("Ivan"));
        System.out.println(result1.size() == 2);

        Map<String, Integer> grades2 = Map.of(
                "John", 2,
                "Bob", 3
        );

        List<String> result2 = findStudentsByMinGrade(grades2, 4);
        System.out.println(result2.isEmpty());

        Map<String, Integer> grades3 = Map.of(
                "Ann", 5,
                "Kate", 5
        );

        List<String> result3 = findStudentsByMinGrade(grades3, 5);
        System.out.println(result3.size() == 2);
        System.out.println(result3.contains("Ann"));
        System.out.println(result3.contains("Kate"));
    }
}
