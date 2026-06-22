package finance.tracker.service;

import finance.tracker.dto.Category;
import finance.tracker.dto.Transaction;
import finance.tracker.dto.TransactionType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static finance.tracker.BudgetApp.*;

public class StatisticsService {
    public void showBalance(ArrayList<Transaction> transactions) {
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
    public void showAvgExpenses (ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getType() == TransactionType.расход){
                sum = sum + transactions.get(i).getAmount();
                count = count + 1;
            }
        }
        int avgExpense = sum / count;
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Средний расход равен: " + avgExpense);
        System.out.println("-------------------------------------------------------------------------------");
    }
    public void showExpensesByCategory (Scanner scanner, ArrayList<Transaction> transactions, MenuService ms) {
        checkEmpty(transactions);
        while (true) {
            ms.printCategoriesMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    boolean found = false;
                    int expenses = 0;
                    for (int i = 0; i < transactions.size(); i++) {
                        if (transactions.get(i).getCategory() == Category.зарплата && transactions.get(i).getType() == TransactionType.расход) {
                            expenses = expenses + transactions.get(i).getAmount();
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("В указанной категории расходов не найдено!");
                        System.out.println("-------------------------------------------------------------------------------");
                    } else {
                        System.out.println("Сумма расхода по категории \"зарплата\": " + expenses);
                    }
                }
                case "2" -> {
                    boolean found = false;
                    int expenses = 0;
                    for (int i = 0; i < transactions.size(); i++) {
                        if (transactions.get(i).getCategory() == Category.еда && transactions.get(i).getType() == TransactionType.расход) {
                            expenses = expenses + transactions.get(i).getAmount();
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("В указанной категории расходов не найдено!");
                        System.out.println("-------------------------------------------------------------------------------");
                    } else {
                        System.out.println("Сумма расхода по категории \"еда\": " + expenses);
                    }
                }
                case "3" -> {
                    boolean found = false;
                    int expenses = 0;
                    for (int i = 0; i < transactions.size(); i++) {
                        if (transactions.get(i).getCategory() == Category.транспорт && transactions.get(i).getType() == TransactionType.расход) {
                            expenses = expenses + transactions.get(i).getAmount();
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("В указанной категории расходов не найдено!");
                        System.out.println("-------------------------------------------------------------------------------");
                    } else {
                        System.out.println("Сумма расхода по категории \"транспорт\": " + expenses);
                    }
                }
                case "4" -> {
                    boolean found = false;
                    int expenses = 0;
                    for (int i = 0; i < transactions.size(); i++) {
                        if (transactions.get(i).getCategory() == Category.развлечения && transactions.get(i).getType() == TransactionType.расход) {
                            expenses = expenses + transactions.get(i).getAmount();
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("В указанной категории расходов не найдено!");
                        System.out.println("-------------------------------------------------------------------------------");
                    } else {
                        System.out.println("Сумма расхода по категории \"развлечения\": " + expenses);
                    }
                }
                case "5" -> {
                    boolean found = false;
                    int expenses = 0;
                    for (int i = 0; i < transactions.size(); i++) {
                        if (transactions.get(i).getCategory() == Category.здоровье && transactions.get(i).getType() == TransactionType.расход) {
                            expenses = expenses + transactions.get(i).getAmount();
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("В указанной категории расходов не найдено!");
                        System.out.println("-------------------------------------------------------------------------------");
                    } else {
                        System.out.println("Сумма расхода по категории \"здоровье\": " + expenses);
                    }
                }
                case "6" -> {
                    boolean found = false;
                    int expenses = 0;
                    for (int i = 0; i < transactions.size(); i++) {
                        if (transactions.get(i).getCategory() == Category.другое && transactions.get(i).getType() == TransactionType.расход) {
                            expenses = expenses + transactions.get(i).getAmount();
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("В указанной категории расходов не найдено!");
                        System.out.println("-------------------------------------------------------------------------------");
                    } else {
                        System.out.println("Сумма расхода по категории \"другое\": " + expenses);
                    }
                }
                case "7" -> {
                    ms.showMenu();
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
    public void showExpensesByMonth (Scanner scanner, ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        System.out.print("Введите месяц от 1-12: ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите год: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;
        boolean printHeader = false;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getType() == TransactionType.расход && transactions.get(i).getDate().getMonthValue() == month && transactions.get(i).getDate().getYear() == year) {
                found = true;
                if (printHeader == false) {
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
                    printHeader = true;
                }
                System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                        transactions.get(i).getId(),
                        transactions.get(i).getAmount(),
                        transactions.get(i).getType(),
                        transactions.get(i).getDate(),
                        transactions.get(i).getCategory(),
                        transactions.get(i).getDiscription());
            }
        }
        System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
        if (found == false) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("За указанный период ничего не найдено");
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
    public void showMaxExpense (ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        int maxExpense = 0;
        boolean found = false;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getType() == TransactionType.расход && transactions.get(i).getAmount() > maxExpense) {
                maxExpense = transactions.get(i).getAmount();
                found = true;
            }
        }
        if (found == false) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Расходов не найдено!");
            System.out.println("-------------------------------------------------------------------------------");
            return;
        };
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getType() == TransactionType.расход && transactions.get(i).getAmount() == maxExpense) {
                System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                        transactions.get(i).getId(),
                        transactions.get(i).getAmount(),
                        transactions.get(i).getType(),
                        transactions.get(i).getDate(),
                        transactions.get(i).getCategory(),
                        transactions.get(i).getDiscription());
                System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
            }
        }
    }
    public void showUniqueCategories (ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        HashSet<Category> uniqueCategories = new HashSet<>();
        for (int i = 0; i < transactions.size(); i++){
            uniqueCategories.add(transactions.get(i).getCategory());
        }
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Список уникальных категорий: " + uniqueCategories);
        System.out.println("-------------------------------------------------------------------------------");
    }
}
