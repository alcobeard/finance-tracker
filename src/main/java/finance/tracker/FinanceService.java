package finance.tracker;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Aleksandr Bagdasarov
 * 2026-04
 */

public class FinanceService {
    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // <-- это флаг
        while (running){
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> transactionAdd(scanner, transactions);
                    case 2 -> showTransactions(transactions);
                    case 3 -> showBalance (transactions);
                    case 4 -> removeTransaction (scanner, transactions);
                    case 5 -> {
                        exit ();
                        return;
                    }
                    default -> {
                        System.out.println("------------------------------------------");
                        System.out.println("Выберете пункт меню!");
                        System.out.println("------------------------------------------");
                    }
                }
            }
            catch(NumberFormatException e) {
                System.out.println("------------------------------------------");
                System.out.println("Ошибка ввода!");
                System.out.println("------------------------------------------");
                System.out.println("Выберете пункт меню!");
                System.out.println("------------------------------------------");
            }
        }
    }
    /**
     * Список методов:
     * showMenu - Вывести главное меню;
     * transactionAdd - Добавить транзакцию;
     * showTransactions - Отобразить список всех операций;
     * showBalance - Расчитать прибыль;
     * removeTransaction - Удалить операцию по ID;
     * exit - Выход из программы;
     *
     * Добавить:
     * Проверку на 3 неправильных ввода, для возврата в главное меню.
     */

    public static void showMenu() {
        System.out.println("------------------------------------------");
        System.out.println("========= Трекер Вашего бюджета ==========");
        System.out.println("------------------------------------------");
        System.out.println("1. Добавить доход или расход");
        System.out.println("2. Отобразить список всех операций");
        System.out.println("3. Расчитать прибыль");
        System.out.println("4. Удалить операцию по ID");
        System.out.println("5. Выход");
        System.out.println("------------------------------------------");
        System.out.print("Пункт меню № ");
    }
    public static void transactionAdd(Scanner scanner, ArrayList<finance.tracker.Transaction> transactions) {
        while (true) {
            try {
                System.out.println("------------------------------------------");
                System.out.println("==Трекер бюджета | Добавление транзакции==");
                System.out.println("------------------------------------------");
                System.out.print("Введите сумму вашего дохода или расхода: ");
                int amount = Integer.parseInt(scanner.nextLine());
                if (amount > 0) {
                    String type = new String(); //scanner.nextLine();
                    while (true) {
                        System.out.println("------------------------------------------");
                        System.out.println("Выберите тип транзакции:");
                        System.out.println("------------------------------------------");
                        System.out.println("1. Доход");
                        System.out.println("2. Расход");
                        System.out.println("3. Вернуться в меню");
                        System.out.println("------------------------------------------");
                        System.out.print("Ваш выбор № ");
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "1" -> type = "доход";
                            case "2" -> type = "расход";
                            case "3" -> {
                                return;
                            }
                            default -> {
                                System.out.println("------------------------------------------");
                                System.out.println("Выберете пункт из списка!");
                                System.out.println("------------------------------------------");
                            }
                        }
                        break;
                    }
                    LocalDate date;
                    while (true) {
                        System.out.print("Введите дату (дд-мм-гггг или дд.мм.гггг): ");
                        String input = scanner.nextLine();

                        if (input.matches("\\d{2}-\\d{2}-\\d{4}") || input.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                            String[] parts;
                            if (input.contains("-")) {
                                parts = input.split("-");
                            } else {
                                parts = input.split("\\.");
                            }

                            String isoDate = parts[2] + "-" + parts[1] + "-" + parts[0];

                            try {
                                date = LocalDate.parse(isoDate);  // ← присваиваем объект LocalDate
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("------------------------------------------");
                                System.out.println("Ошибка! Такой даты не существует");
                                System.out.println("------------------------------------------");
                            }
                        } else {
                            System.out.println("Ошибка! Используйте формат дд-мм-гггг или дд.мм.гггг");
                        }
                    }
                    int Id = transactions.size() + 1;
                    finance.tracker.Transaction newTransaction = new finance.tracker.Transaction(Id, type, amount, date);
                    transactions.add(newTransaction);
                    System.out.println("------------------------------------------");
                    System.out.println("       Транзакция успешно добавлена!      ");
                    System.out.println("------------------------------------------");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %n", "ID", "Сумма", "Тип", "Дата");
                    System.out.println("|------|-----------|--------|------------|");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %n", newTransaction.id, newTransaction.amount, newTransaction.type, newTransaction.date);
                    System.out.println("------------------------------------------");
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("------------------------------------------");
                System.out.println("Ошибка ввода!");
            }
        }
    }
    public static void showTransactions (ArrayList<finance.tracker.Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("------------------------------------------");
        } else {
            System.out.println("------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %n", "ID", "Сумма", "Тип", "Дата");
            System.out.println("|------|-----------|--------|------------|");
            for (int i = 0; i < transactions.size(); i++) {
                System.out.printf("| %-4s | %-9s | %-6s | %-10s | %n", transactions.get(i).id, transactions.get(i).amount, transactions.get(i).type, transactions.get(i).date);
                System.out.println("|------|-----------|--------|------------|");
            }
        }
    }
    public static void showBalance (ArrayList<finance.tracker.Transaction> transactions) {
        System.out.println("------------------------------------------");
        System.out.println("===  Трекер бюджета | Рассчёт прибыли  ===");
        System.out.println("------------------------------------------");
        int prices = 0;
        if (transactions.isEmpty()) {                          // transactions.size = 0 не работает
            System.out.println("------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("------------------------------------------");
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).type.equals("доход")) {                        // (transactions.get(i).get("type") == "доход" не работает
                    prices = transactions.get(i).amount + prices;
                } else {
                    prices = prices - transactions.get(i).amount;
                }
            }
            System.out.println("Ваша прибыль составляет: " + prices);
        }
    }
    public static void removeTransaction (Scanner scanner, ArrayList<finance.tracker.Transaction> transactions){
        System.out.println("------------------------------------------");
        System.out.print("Введите ID операции: ");
        String input = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < transactions.size(); i++) {                   //долго ломал голову, не знал, что if без else работает
            if (String.valueOf(transactions.get(i).id).equals(input)) {
                transactions.remove(i);
                found = true;
                System.out.println("------------------------------------------");
                System.out.println("Операция с ID: " + input +" удалена!");
                System.out.println("------------------------------------------");
                break;
            }
        }
        if (found == false) {
            System.out.println("------------------------------------------");
            System.out.println("Операции с ID:" + input + " не существует!");
            System.out.println("------------------------------------------");
        }
    }
    public static void exit () {
        System.out.println("------------------------------------------");
        System.out.println("              До свидания!                ");
        System.out.println("------------------------------------------");
    }
}