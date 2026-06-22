package finance.tracker.service;

import finance.tracker.dto.Category;
import finance.tracker.dto.Transaction;
import finance.tracker.dto.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static finance.tracker.BudgetApp.checkEmpty;
import static finance.tracker.Util.Utils.inputDate;


public class DisplayService {
    public void showAllTransactions(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("-------------------------------------------------------------------------------");
        } else {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
            System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
            for (int i = 0; i < transactions.size(); i++) {
                System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                        transactions.get(i).getId(),
                        transactions.get(i).getAmount(),
                        transactions.get(i).getType(),
                        transactions.get(i).getDate(),
                        transactions.get(i).getCategory(),
                        transactions.get(i).getDiscription()
                );
                System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
            }
        }
    }
    public void showIncome(ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
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
    public void showExpense(ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
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
    public void showByCategory(ArrayList<Transaction> transactions, Category category) {
        checkEmpty(transactions);
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
    public void showTransactionsByDate(Scanner scanner, ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        LocalDate date = inputDate(scanner);
        boolean found = false;
        boolean printHeader = false;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getDate().equals(date)) {
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
                System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
            }
        }
        if (found == false) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("За указанную дату ничего не найдено!");
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
    public void showTransactionsByDateRange(Scanner scanner, ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        LocalDate startDate = null;
        LocalDate endDate = null;
        System.out.println("Введите дату начала поиска!");
        LocalDate firstDate = inputDate(scanner);
        System.out.println("Введите дату окончания поиска!");
        LocalDate secondDate = inputDate(scanner);
        if (firstDate.isAfter(secondDate)) {
            endDate = firstDate;
            startDate = secondDate;
        } else {
            startDate = firstDate;
            endDate = secondDate;
        }
        boolean found = false; // <- Флаг
        boolean printHeader = false; // <- Флаг
        for (int i = 0; i < transactions.size(); i++) {
            if (!transactions.get(i).getDate().isBefore(startDate) && !transactions.get(i).getDate().isAfter(endDate)) {
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
        if (found == false) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("За указанный период ничего не найдено");
            System.out.println("-------------------------------------------------------------------------------");
        }
        else {
            System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
        }
    }
    public void showTransactionsByDescription(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.print("Введите искомое описание: ");
        String input = scanner.nextLine();
        if (transactions.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("-------------------------------------------------------------------------------");
            return;
        }
        boolean found = false;
        boolean printHeader = false;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getDiscription().toLowerCase().contains(input)) {
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
        if (found == false) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Транзакции с таким описанием не обнаружены");
            System.out.println("-------------------------------------------------------------------------------");
        }
        else {
            System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
        }
    }
    public void printTransactionsTable (ArrayList<Transaction> transactions) {
        boolean printHeader = false;
        for (int i = 0; i < transactions.size(); i++) {
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
        System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
    }
}
