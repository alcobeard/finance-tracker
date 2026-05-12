package finance.tracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author Aleksandr Bagdasarov
 * 2026-05
 */

public class FinanceService {
    private static final List<HashMap<String, String>> transactions = new ArrayList<>();
    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
    );
    static int balance = 0;


    public static void main(String[] args) {

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

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
        while (true) { // Наша программа должна работать, пока мы не захотим из нее выйти. Подумайте,
            // как нам показывать меню до тех пор, пока не будет выбрано выйти из программы
            showMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addTranscation();
                    break;
                case "2":
                    listTransactions();
                    break;
                case "3":
                    showBalance();
                    break;
                case "4":
                    deleteTransactions();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }

    private static void deleteTransactions() {
        listTransactions();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter transaction ID to be deleted: ");
        String id = scanner.nextLine();
        for  (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).get(id).equals(id)) {
                int amount = Integer.parseInt(transactions.get(i).get("amount"));
                String type = transactions.get(i).get("type");
                balance += (type.equals("income")) ? amount * (-1) : amount;
                transactions.remove(i);
                System.out.println(id + " transaction has been deleted");
                break;
            }
            else {
                System.out.println("ID not found");
            }
        }
    }

    private static void showBalance() {
        System.out.println("Balance: " + balance);
    }

    private static void addTranscation() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> transaction = new HashMap<>();
        int id = transactions.size() + 1;

        transaction.put("id", String.valueOf(id));

        System.out.print("Enter amount to add: ");
        String amount = scanner.nextLine();
        transaction.put("amount", amount);
        boolean validTransaction = false;
        while (!validTransaction) {
            System.out.print("Enter type of transaction - 1. income / 2. expense: ");
            String type = scanner.nextLine();
            switch (type) {
                case "1": balance = balance + Integer.parseInt(amount); transaction.put("type", "income"); validTransaction = true; break;
                case "2": balance = balance - Integer.parseInt(amount); transaction.put("type", "expense"); validTransaction = true; break;
                default: System.out.println("Invalid type");
            }
        }

        boolean validDate = false;

        while (!validDate) {
            System.out.print("Enter date of transaction: ");
            String dateInput = scanner.nextLine();
            LocalDate date;
            try {
                date = parseDate(dateInput);
                transaction.put("date", String.valueOf(date));
                validDate = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        transactions.add(transaction);
        printTransaction(transaction);
    }

    // Давайте выведим демонстрацию меню в отдельную функцию для удобства.
    public static void showMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          PERSONAL FINANCE APP        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Add income or expense            ║");
        System.out.println("║  2. Show transaction list            ║");
        System.out.println("║  3. Show balance                     ║");
        System.out.println("║  4. Delete transaction by ID         ║");
        System.out.println("║  5. Exit application                 ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Choose an option: ");
    }

    private static void printTransaction(HashMap<String, String> transaction) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("+----+----------+----------+------------+");
        System.out.println("| ID | TYPE     | AMOUNT   | DATE       |");
        System.out.println("+----+----------+----------+------------+");

        System.out.printf(
                "| %-2s | %-8s | %-8s | %-10s |%n",
                transaction.get("id"),
                transaction.get("type"),
                transaction.get("amount"),
                transaction.get("date")
        );

        System.out.println("+----+----------+----------+------------+");
    }

    public static void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("+----+----------+----------+------------+");
        System.out.println("| ID | TYPE     | AMOUNT   | DATE       |");
        System.out.println("+----+----------+----------+------------+");

        for (HashMap<String, String> transaction : transactions) {
            System.out.printf(
                    "| %-2s | %-8s | %-8s | %-10s |%n",
                    transaction.get("id"),
                    transaction.get("type"),
                    transaction.get("amount"),
                    transaction.get("date")
            );
        }

        System.out.println("+----+----------+----------+------------+");
    }

    private static LocalDate parseDate(String input) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
                // try next format
            }
        }
        throw new IllegalArgumentException(
                "Invalid date format. Supported formats: dd-MM-yyyy, dd/MM/yyyy, yyyy-MM-dd, yyyy/MM/dd, dd.MM.yyyy"
        );
    }
}
