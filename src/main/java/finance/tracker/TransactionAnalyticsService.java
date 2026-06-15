package finance.tracker;

import finance.tracker.dto.Transaction;
import java.util.List;
import java.util.Scanner;
import finance.tracker.dto.Category;
import finance.tracker.dto.TransactionType;
import java.util.EnumMap;
import java.util.Map;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.Set;

public class TransactionAnalyticsService {
    private final List<Transaction> transactions;
    private final Scanner scanner;
    public TransactionAnalyticsService(List<Transaction> transactions, Scanner scanner) {
        this.transactions = transactions;
        this.scanner = scanner;
    }
    public void showAnalyticsMenu() {
        boolean analyticsMenuRunning = true;
        while (analyticsMenuRunning) {
            System.out.println("Меню аналитики");
            System.out.println("1. Посчитать расходы по каждой категории");
            System.out.println("2. Найти самую большую трату");
            System.out.println("3. Посчитать расходы за месяц");
            System.out.println("4. Посчитать средний расход");
            System.out.println("5. Вывести уникальные категории");
            System.out.println("6. Назад");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "1": {
                    showExpensesByCategory();
                    break;
                }
                case "2": {
                    showMaxExpense();
                    break;
                }
                case "3": {
                    showExpensesByMonth();
                    break;
                }
                case "4": {
                    showAverageExpense();
                    break;
                }
                case "5": {
                    showUniqueCategories();
                    break;
                }
                case "6": {
                    analyticsMenuRunning = false;
                    System.out.println("Возврат в главное меню");
                    break;
                }
                default: {
                    System.out.println("Неизвестная команда");
                    break;
                }
            }
        }
    }
    private void showExpensesByCategory() {
        Map<Category, Double> expensesByCategory = new EnumMap<>(Category.class);
        for (Category category : Category.values()) {
            expensesByCategory.put(category, 0.0);
        }
        boolean hasExpenses = false;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                Category category = transaction.getCategory();
                double currentAmount = expensesByCategory.get(category);

                expensesByCategory.put(category, currentAmount + transaction.getAmount());
                hasExpenses = true;
            }
        }
        if (!hasExpenses) {
            System.out.println("Расходов пока нет");
            return;
        }
        System.out.println("Расходы по категориям:");
        for (Map.Entry<Category, Double> entry : expensesByCategory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    private void showMaxExpense() {
        Transaction maxExpense = null;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                if (maxExpense == null || transaction.getAmount() > maxExpense.getAmount()) {
                    maxExpense = transaction;
                }
            }
        }
        if (maxExpense == null) {
            System.out.println("Расходов пока нет");
            return;
        }
        System.out.println("Самая большая трата:");
        FinanceService.printTransaction(maxExpense);
    }
    private void showExpensesByMonth() {
        System.out.println("Введите месяц в формате ММ.ГГГГ");
        String input = scanner.nextLine().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");
        YearMonth searchMonth = YearMonth.parse(input, formatter);
        double totalExpenses = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                YearMonth transactionMonth = YearMonth.from(transaction.getDate());
                if (transactionMonth.equals(searchMonth)) {
                    totalExpenses = totalExpenses + transaction.getAmount();
                }
            }
        }
        System.out.println("Расходы за месяц " + input + ": " + totalExpenses);
    }
    private void showAverageExpense() {
        double totalExpenses = 0;
        int expenseCount = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                totalExpenses = totalExpenses + transaction.getAmount();
                expenseCount++;
            }
        }
        if (expenseCount == 0) {
            System.out.println("Расходов пока нет");
            return;
        }
        double averageExpense = totalExpenses / expenseCount;
        System.out.println("Средний расход: " + averageExpense);
    }
    private void showUniqueCategories() {
        Set<Category> uniqueCategories = EnumSet.noneOf(Category.class);
        for (Transaction transaction : transactions) {
            uniqueCategories.add(transaction.getCategory());
        }
        if (uniqueCategories.isEmpty()) {
            System.out.println("Операций пока нет");
            return;
        }
        System.out.println("Уникальные категории в операциях:");
        for (Category category : uniqueCategories) {
            System.out.println(category);
        }
    }
}
