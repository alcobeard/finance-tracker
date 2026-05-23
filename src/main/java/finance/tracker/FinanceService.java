package finance.tracker;
import finance.tracker.dto.Transaction;
import finance.tracker.dto.TransactionType;
import finance.tracker.dto.Category;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Aleksandr Bagdasarov
 * 2026-04
 */

public class FinanceService {
    static int prices = 0;
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
                    case 2 -> transactionList(scanner, transactions);
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
     * П.П   Описание функционала:                  Методы:
     *       Отобразить - Главное меню:             showMenu
     * 1.    Добавить транзакцию                    transactionAdd
     * 2.    Отобразить список операций             transactionList
     * 2.1.  Отобразить список всех операций        showAllTransactions
     * 2.1.  Отобразить список доходов              showIncome
     * 2.2.  Отобразить список расходов             showExpense
     * 2.3.  Фильтрация по категориям               filterByCategory
     * 2.3.1 Отображение списка с категорией        showByCategory
     * 3.    Расчитать прибыль                      showBalance
     * 4.    Удалить операцию по ID                 removeTransaction
     * 5.    Выход                                  exit
     *
     * Добавить:
     Подумать над выходом из вопросов.
     */

    public static void showMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("================ Трекер Вашего бюджета =================");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Добавить доход или расход");
        System.out.println("2. Отобразить список операций");
        System.out.println("3. Расчитать прибыль");
        System.out.println("4. Удалить операцию по ID");
        System.out.println("5. Выход");
        System.out.println("--------------------------------------------------------");
        System.out.print("Пункт меню № ");
    }
    public static void transactionAdd(Scanner scanner, ArrayList<Transaction> transactions) {
        while (true) {
            try {
                System.out.println("--------------------------------------------------------");
                System.out.println("======== Трекер бюджета | Добавление транзакции ========");
                System.out.println("--------------------------------------------------------");
                System.out.print("Введите сумму вашего дохода или расхода: ");
                int amount = Integer.parseInt(scanner.nextLine());
                if (amount > 0) {
                    TransactionType type = TransactionType.доход; //scanner.nextLine();
                    while (true) {
                        System.out.println("--------------------------------------------------------");
                        System.out.println("Выберите тип транзакции:");
                        System.out.println("--------------------------------------------------------");
                        System.out.println("1. Доход");
                        System.out.println("2. Расход");
                        System.out.println("3. Вернуться в меню");
                        System.out.println("--------------------------------------------------------");
                        System.out.print("Ваш выбор № ");
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "1" -> type = TransactionType.доход;
                            case "2" -> type = TransactionType.расход;
                            case "3" -> {
                                return;
                            }
                            default -> {
                                System.out.println("--------------------------------------------------------");
                                System.out.println("Выберете пункт из списка!");
                                System.out.println("--------------------------------------------------------");
                            }
                        }
                        break;
                    }
                    Category category = Category.еда;
                    while (true) {
                        System.out.println("--------------------------------------------------------");
                        System.out.println("Выберите категорию:");
                        System.out.println("--------------------------------------------------------");
                        System.out.println("1. Зарплата");
                        System.out.println("2. Еда");
                        System.out.println("3. Транспорт");
                        System.out.println("4. Развлечения");
                        System.out.println("5. Здоровье");
                        System.out.println("6. Другое");
                        System.out.println("7. Вернуться в меню");
                        System.out.println("--------------------------------------------------------");
                        System.out.print("Ваш выбор № ");
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "1" -> category = Category.зарплата;
                            case "2" -> category = Category.еда;
                            case "3" -> category = Category.транспорт;
                            case "4" -> category = Category.развлечения;
                            case "5" -> category = Category.здоровье;
                            case "6" -> category = Category.другое;
                            case "7" -> {
                                return;
                            }
                            default -> {
                                System.out.println("--------------------------------------------------------");
                                System.out.println("Выберете пункт из списка!");
                                System.out.println("--------------------------------------------------------");
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
                                date = LocalDate.parse(isoDate);
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("--------------------------------------------------------");
                                System.out.println("Ошибка! Такой даты не существует");
                                System.out.println("--------------------------------------------------------");
                            }
                        } else {
                            System.out.println("Ошибка! Используйте формат дд-мм-гггг или дд.мм.гггг");
                        }
                    }
                    if (type == TransactionType.доход) {
                        prices = prices + amount;
                    }
                    else {
                        prices = prices - amount;
                    }
                    int Id = transactions.size() + 1;
                    Transaction newTransaction = new Transaction(Id, type, amount, date, category);
                    transactions.add(newTransaction);
                    System.out.println("--------------------------------------------------------");
                    System.out.println("              Транзакция успешно добавлена!             ");
                    System.out.println("--------------------------------------------------------");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", "ID", "Сумма", "Тип", "Дата", "Категория");
                    System.out.println("|------|-----------|--------|------------|-------------|");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", newTransaction.getId(), newTransaction.getAmount(), newTransaction.getType(), newTransaction.getDate(), newTransaction.getCategory());
                    System.out.println("--------------------------------------------------------");
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("--------------------------------------------------------");
                System.out.println("Ошибка ввода!");
            }
        }
    }
    public static void transactionList (Scanner scanner, ArrayList <Transaction> transactions) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Сделайте выбор:");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Отобразить список всех операций");
        System.out.println("2. Отобразить список доходов");
        System.out.println("3. Отобразить список расодов");
        System.out.println("4. Фильтрация по категориям");
        System.out.println("5. Вернуться в главное меню");
        System.out.println("--------------------------------------------------------");
        System.out.print("Ваш выбор № ");
        String choice =  scanner.nextLine();
        switch (choice) {
            case "1" -> showAllTransactions(transactions);
            case "2" -> showIncome(transactions);
            case "3" -> showExpense(transactions);
            case "4" -> filterByCategory(scanner, transactions);
            case "5" -> {
                return;
            }
            default -> {
                System.out.println("--------------------------------------------------------");
                System.out.println("Выберете пункт из списка!");
                System.out.println("--------------------------------------------------------");
            }
        }
    }
    public static void showAllTransactions (ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("--------------------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", "ID", "Сумма", "Тип", "Дата", "Категория");
            System.out.println("|------|-----------|--------|------------|-------------|");
            for (int i = 0; i < transactions.size(); i++) {
                System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", transactions.get(i).getId(), transactions.get(i).getAmount(), transactions.get(i).getType(), transactions.get(i).getDate(), transactions.get(i).getCategory());
                System.out.println("|------|-----------|--------|------------|-------------|");
            }
        }
    }
    public static void showIncome (ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("--------------------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", "ID", "Сумма", "Тип", "Дата", "Категория");
            System.out.println("|------|-----------|--------|------------|-------------|");
            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).getType() == TransactionType.доход) {
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", transactions.get(i).getId(), transactions.get(i).getAmount(), transactions.get(i).getType(), transactions.get(i).getDate(), transactions.get(i).getCategory());
                    System.out.println("|------|-----------|--------|------------|-------------|");
                }
            }
        }
    }
    public static void showExpense (ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("--------------------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", "ID", "Сумма", "Тип", "Дата", "Категория");
            System.out.println("|------|-----------|--------|------------|-------------|");
            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).getType() == TransactionType.расход) {
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", transactions.get(i).getId(), transactions.get(i).getAmount(), transactions.get(i).getType(), transactions.get(i).getDate(), transactions.get(i).getCategory());
                    System.out.println("|------|-----------|--------|------------|-------------|");
                }
            }
        }
    }
    public static void showBalance (ArrayList<Transaction> transactions) {
        System.out.println("--------------------------------------------------------");
        System.out.println("======      Трекер бюджета | Рассчёт прибыли      ======");
        System.out.println("--------------------------------------------------------");
        if (transactions.isEmpty()) {                          // transactions.size = 0 не работает
            System.out.println("--------------------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("Ваша прибыль составляет: 0");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("Ваша прибыль составляет: " + prices);
                }
    }
    public static void removeTransaction (Scanner scanner, ArrayList<Transaction> transactions){
        System.out.println("--------------------------------------------------------");
        System.out.print("Введите ID операции: ");
        String input = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < transactions.size(); i++) {                   //долго ломал голову, не знал, что if без else работает
            if (String.valueOf(transactions.get(i).getId()).equals(input) && transactions.get(i).getType() == TransactionType.доход) {
                prices = prices - transactions.get(i).getAmount();
                transactions.remove(i);
                found = true;
                System.out.println("--------------------------------------------------------");
                System.out.println("Операция с ID: " + input +" удалена!");
                System.out.println("--------------------------------------------------------");
                break;
            }
            if (String.valueOf(transactions.get(i).getId()).equals(input) && transactions.get(i).getType() == TransactionType.расход) {
                prices = prices + transactions.get(i).getAmount();
                transactions.remove(i);
                found = true;
                System.out.println("--------------------------------------------------------");
                System.out.println("Операция с ID: " + input +" удалена!");
                System.out.println("--------------------------------------------------------");
                break;
            }
        }
        if (found == false) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Операции с ID:" + input + " не существует!");
            System.out.println("--------------------------------------------------------");
        }
    }
    public static void exit () {
        System.out.println("--------------------------------------------------------");
        System.out.println("                     До свидания!                       ");
        System.out.println("--------------------------------------------------------");
    }
    public static void filterByCategory (Scanner scanner, ArrayList <Transaction> transactions) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Выберете категорию:");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Зарплата");
        System.out.println("2. Еда");
        System.out.println("3. Транспорт");
        System.out.println("4. Развлечение");
        System.out.println("5. Здоровье");
        System.out.println("6. Другое");
        System.out.println("7. Вернуться в главное меню");
        System.out.println("--------------------------------------------------------");
        System.out.print("Ваш выбор № ");
        String choice =  scanner.nextLine();
        switch (choice) {
            case "1" -> showByCategory(transactions, Category.зарплата);
            case "2" -> showByCategory(transactions, Category.еда);
            case "3" -> showByCategory(transactions, Category.транспорт);
            case "4" -> showByCategory(transactions, Category.развлечения);
            case "5" -> showByCategory(transactions, Category.здоровье);
            case "6" -> showByCategory(transactions, Category.другое);
            case "7" -> {
                return;
            }
            default -> {
                System.out.println("--------------------------------------------------------");
                System.out.println("Выберете пункт из списка!");
                System.out.println("--------------------------------------------------------");
            }
        }
    }
    public static void showByCategory (ArrayList <Transaction> transactions, Category category){
        if (transactions.isEmpty()) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("--------------------------------------------------------");
        } else {
            System.out.println("--------------------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", "ID", "Сумма", "Тип", "Дата", "Категория");
            System.out.println("|------|-----------|--------|------------|-------------|");
            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).getCategory() == category) {
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", transactions.get(i).getId(), transactions.get(i).getAmount(), transactions.get(i).getType(), transactions.get(i).getDate(), transactions.get(i).getCategory());
                    System.out.println("|------|-----------|--------|------------|-------------|");
                }
            }
        }
    }
}