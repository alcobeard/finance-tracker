package finance.tracker;

/**
 * @author Aleksandr Bagdasarov
 * 2026-04
 */

public class Main {
    public static void main(String[] args) {
        /***
         * Изменить этот метод, чтобы он выводил сообщение вида:
         * Welcome to Finance Tracker
         * 1. Add transaction
         * 2. List transactions
         * 3. Exit
         *
         * Никакого функционала на текущий момент за этим не должно быть. Просто запуск приложения -> вывод нового сообщения -> завершение работы
         *
         * Обратите внимание, что должно быть 4 строки
         */
        String[] menuItems = {
                "Add transaction",
                "List transactions",
                "Exit"
        };

        printMenu("Welcome to Finance Tracker", menuItems);
    }

    public static void printMenu(String title, String[] items) {
        System.out.println("================================");
        System.out.println("     " + title);
        System.out.println("================================");

        for (int i = 0; i < items.length; i++) {
            System.out.println("  " + (i + 1) + ". " + items[i]);
        }

        System.out.println("--------------------------------");
        System.out.print("Choose an option: ");
    }
}