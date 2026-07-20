package finance.tracker.service;

import finance.tracker.dto.Category;
import finance.tracker.model.Budget;

import java.util.Scanner;


public class MenuService {
    private final Scanner scanner;
    private final TransactionService ts;
    private final SortAndFindService saf;
    public MenuService(Scanner scanner, TransactionService ts, SortAndFindService saf) {
        this.scanner = scanner;
        this.ts = ts;
        this.saf = saf;

    }

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

    public void transactionList(Scanner scanner, Budget budget) {
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
                case "1" -> showList(scanner, budget);
                case "2" -> searchTransactions(scanner, budget);
                case "3" -> sortAndAnalysis(scanner, budget);
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

    public void filterByCategory(Scanner scanner, Budget budget) {
        printCategoriesMenu();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> saf.showByCategory(budget, Category.зарплата);
            case "2" -> saf.showByCategory(budget, Category.еда);
            case "3" -> saf.showByCategory(budget, Category.транспорт);
            case "4" -> saf.showByCategory(budget, Category.развлечения);
            case "5" -> saf.showByCategory(budget, Category.здоровье);
            case "6" -> saf.showByCategory(budget, Category.другое);
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

    public static void printCategoriesMenu() {
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

    public void showList(Scanner scanner, Budget budget) {
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
                case "1" -> saf.showAllTransactions(budget);
                case "2" -> saf.showIncome(budget);
                case "3" -> saf.showExpense(budget);
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

    public void searchTransactions(Scanner scanner, Budget budget) {
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
                case "1" -> filterByCategory(scanner, budget);
                case "2" -> saf.showTransactionsByDate(scanner, budget);
                case "3" -> saf.showTransactionsByDateRange(scanner, budget);
                case "4" -> saf.showTransactionsByDescription(scanner, budget);
                case "5" -> transactionList(scanner, budget);
                default -> {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Выберете пункт из списка!");
                    System.out.println("--------------------------------------------------------");
                }
            }
        }
    }

    public void sortAndAnalysis(Scanner scanner, Budget budget) {
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
                case "1" -> {
                    ts.sortTransactionsByDate(scanner, budget);
                    saf.printTransactionsTable(budget);
                }
                case "2" -> {
                    ts.sortTransactionsByAmount(scanner, budget);
                    saf.printTransactionsTable(budget);
                }
                case "3" -> saf.showExpensesByCategory(scanner, budget);
                case "4" -> saf.showMaxExpense(budget);
                case "5" -> saf.showExpensesByMonth(scanner, budget);
                case "6" -> saf.showAvgExpenses(budget);
                case "7" -> saf.showUniqueCategories(budget);
                case "8" -> transactionList(scanner, budget);
                default -> {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Выберете пункт из списка!");
                    System.out.println("--------------------------------------------------------");
                }
            }
        }
    }

    public void addTransactionFlow(Scanner scanner, Budget budget) {
        Category category = choiceCategory(scanner);
        if (category == null) {
            return; // пользователь вернулся в меню
        }
        ts.transactionAdd(scanner, budget, category);
    }
}