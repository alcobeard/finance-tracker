package finance.tracker.service;

import finance.tracker.dto.Category;
import finance.tracker.dto.Transaction;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuService {
    public void showMenu() {
        System.out.println("                                                        ");
        System.out.println("================ Трекер Вашего бюджета =================");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Добавить доход или расход");
        System.out.println("2. Отобразить список операций");
        System.out.println("3. Расчитать прибыль");
        System.out.println("4. Удалить операцию по ID");
        System.out.println("5. Выход");
        System.out.println("* Нажми 9 Для генерации данных");
        System.out.println("--------------------------------------------------------");
        System.out.print("Пункт меню № ");
    }

    public void transactionList(Scanner scanner, ArrayList<Transaction> transactions, TransactionService ts, DisplayService ds, StatisticsService ss) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Сделайте выбор:");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Просмотр списков");
            System.out.println("2. Поиск и фильтрация");
            System.out.println("3. Сортировка и аналитика");
            System.out.println("4. Вернуться в главное меню");
            System.out.println("--------------------------------------------------------");
            System.out.print("Ваш выбор № ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> showList(scanner, transactions, ts, ds, ss);
                case "2" -> searchTransactions(scanner, transactions, ts, ds, ss);
                case "3" -> sortAndAnalysis(scanner, transactions, ts, ds, ss);
                case "4" -> {
                    return;
                }
                default -> {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Выберете пункт из списка!");
                    System.out.println("--------------------------------------------------------");
                }
            }
        }
    }

    public void filterByCategory(Scanner scanner, ArrayList<Transaction> transactions, DisplayService ds) {
        printCategoriesMenu();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> ds.showByCategory(transactions, Category.зарплата);
            case "2" -> ds.showByCategory(transactions, Category.еда);
            case "3" -> ds.showByCategory(transactions, Category.транспорт);
            case "4" -> ds.showByCategory(transactions, Category.развлечения);
            case "5" -> ds.showByCategory(transactions, Category.здоровье);
            case "6" -> ds.showByCategory(transactions, Category.другое);
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

    public Category choiceCategory(Scanner scanner) {
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
                case "1" -> {
                    return Category.зарплата;
                }
                case "2" -> {
                    return Category.еда;
                }
                case "3" -> {
                    return Category.транспорт;
                }
                case "4" -> {
                    return Category.развлечения;
                }
                case "5" -> {
                    return Category.здоровье;
                }
                case "6" -> {
                    return Category.другое;
                }
                case "7" -> {
                    return null;
                }
                default -> {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Выберете пункт из списка!");
                    System.out.println("--------------------------------------------------------");
                }
            }
        }
    }

    public void printCategoriesMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Выберете категорию:");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Зарплата");
        System.out.println("2. Еда");
        System.out.println("3. Транспорт");
        System.out.println("4. Развлечения");
        System.out.println("5. Здоровье");
        System.out.println("6. Другое");
        System.out.println("7. Вернуться в главное меню");
        System.out.println("--------------------------------------------------------");
        System.out.print("Ваш выбор № ");
    }

    public void showList(Scanner scanner, ArrayList<Transaction> transactions, TransactionService ts, DisplayService ds, StatisticsService ss) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Сделайте выбор:");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Отобразить список всех операций");
            System.out.println("2. Отобразить список доходов");
            System.out.println("3. Отобразить список расходов");
            System.out.println("4. Вернуться в предыдущее меню");
            System.out.println("--------------------------------------------------------");
            System.out.print("Ваш выбор № ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> ds.showAllTransactions(transactions);
                case "2" -> ds.showIncome(transactions);
                case "3" -> ds.showExpense(transactions);
                case "4" -> {
                    return;
                }
                default -> {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Выберете пункт из списка!");
                    System.out.println("--------------------------------------------------------");
                }
            }
        }
    }

    public void searchTransactions(Scanner scanner, ArrayList<Transaction> transactions, TransactionService ts, DisplayService ds, StatisticsService ss) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Сделайте выбор:");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Поиск транзакции по категориям");
            System.out.println("2. Поиск транзакций за конкретную дату");
            System.out.println("3. Поиск транзакций за период дат");
            System.out.println("4. Поиск транзакций по описанию");
            System.out.println("5. Вернуться в предыдущее меню");
            System.out.println("--------------------------------------------------------");
            System.out.print("Ваш выбор № ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> filterByCategory(scanner, transactions, ds);
                case "2" -> ds.showTransactionsByDate(scanner, transactions);
                case "3" -> ds.showTransactionsByDateRange(scanner, transactions);
                case "4" -> ds.showTransactionsByDescription(scanner, transactions);
                case "5" -> transactionList(scanner, transactions, ts, ds, ss);
                default -> {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Выберете пункт из списка!");
                    System.out.println("--------------------------------------------------------");
                }
            }
        }
    }

    public void sortAndAnalysis(Scanner scanner, ArrayList<Transaction> transactions, TransactionService ts, DisplayService ds, StatisticsService ss) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Сделайте выбор:");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Сортировка транзакций по дате");
            System.out.println("2. Сортировка транзакций по сумме");
            System.out.println("3. Сортировка расходов по категориям");
            System.out.println("4. Отобразить самую большую трату");
            System.out.println("5. Отобразить расходы за месяц");
            System.out.println("6. Отобразить средний расход");
            System.out.println("7. Отобразить список уникальных категорий");
            System.out.println("8. Вернуться в предыдущее меню");
            System.out.println("--------------------------------------------------------");
            System.out.print("Ваш выбор № ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> ts.sortTransactionsByDate(scanner, transactions, ds, this);
                case "2" -> ts.sortTransactionsByAmount(scanner, transactions, ds, this);
                case "3" -> ss.showExpensesByCategory(scanner, transactions, this);
                case "4" -> ss.showMaxExpense(transactions);
                case "5" -> ss.showExpensesByMonth(scanner, transactions);
                case "6" -> ss.showAvgExpenses(transactions);
                case "7" -> ss.showUniqueCategories(transactions);
                case "8" -> transactionList(scanner, transactions, ts, ds, ss);
                default -> {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Выберете пункт из списка!");
                    System.out.println("--------------------------------------------------------");
                }
            }
        }
    }
}