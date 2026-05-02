package finance.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Aleksandr Bagdasarov
 * 2026-05
 */

public class FinanceService {
    public static void main(String[] args) {
        int balance = 0;
        boolean running = true;

        // Разобраться, что это за структура. Как она выглядит, для чего она и как с ней работать
        List<HashMap<String, String>> transactions = new ArrayList<>();
//      Давайте пока что остановимся на текущем формате хранения/входных данных
//        {
//            "id"       -> "1",
//            "type"     -> "EXPENSE",
//            "amount"   -> "500",
//            "date"     -> "2026-04-29",
//        }

        /**
         * Реализовать функионал финансового трекера с взаимодействием через консоль
         *
         * Функционал
         * 1. Добавить доход/расход
         * 2. Вывести список операций
         * 3. Показать баланс
         * 4. Удалить операцию по id
         * 5. Выйти из программы
         */
        while (running) { // Наша программа должна работать, пока мы не захотим из нее выйти. Подумайте,
            // как нам показывать меню до тех пор, пока не будет выбрано выйти из программы
            showMenu();

        }

    }

    // Давайте выведим демонстрацию меню в отдельную функцию для удобства.
    public static void showMenu() {

    }
}
