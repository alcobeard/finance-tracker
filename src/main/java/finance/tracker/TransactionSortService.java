package finance.tracker;

import finance.tracker.dto.Transaction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TransactionSortService {
    private final List<Transaction> transactions;
    private final Scanner scanner;
    public TransactionSortService(List<Transaction> transactions, Scanner scanner) {
        this.transactions = transactions;
        this.scanner = scanner;
    }
    public void showSortMenu() {
        boolean sortMenuRunning = true;
        while (sortMenuRunning) {
            System.out.println("Меню сортировки");
            System.out.println("1. По дате: от старых к новым");
            System.out.println("2. По дате: от новых к старым");
            System.out.println("3. По сумме: от маленьких к большим");
            System.out.println("4. По сумме: от больших к маленьким");
            System.out.println("5. Назад");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "1": {
                    showTransactionsSortedByDateAsc();
                    break;
                }
                case "2": {
                    showTransactionsSortedByDateDesc();
                    break;
                }
                case "3": {
                    showTransactionsSortedByAmountAsc();
                    break;
                }
                case "4": {
                    showTransactionsSortedByAmountDesc();
                    break;
                }
                case "5": {
                    sortMenuRunning = false;
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
    private void showTransactionsSortedByDateAsc() {
        if (transactions.isEmpty()) {
            System.out.println("Пока нет операций");
            return;
        }
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        sortedTransactions.sort(Comparator.comparing(Transaction::getDate));
        for (Transaction transaction : sortedTransactions) {
            FinanceService.printTransaction(transaction);
        }
    }
    private void showTransactionsSortedByDateDesc() {
        if (transactions.isEmpty()) {
            System.out.println("Пока нет операций");
            return;
        }
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        sortedTransactions.sort(Comparator.comparing(Transaction::getDate).reversed());
        for (Transaction transaction : sortedTransactions) {
            FinanceService.printTransaction(transaction);
        }
    }
    private void showTransactionsSortedByAmountAsc() {
        if (transactions.isEmpty()) {
            System.out.println("Пока нет операций");
            return;
        }
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        sortedTransactions.sort(Comparator.comparing(Transaction::getAmount));
        for (Transaction transaction : sortedTransactions) {
            FinanceService.printTransaction(transaction);
        }
    }
    private void showTransactionsSortedByAmountDesc() {
        if (transactions.isEmpty()) {
            System.out.println("Пока нет операций");
            return;
        }
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        sortedTransactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
        for (Transaction transaction : sortedTransactions) {
            FinanceService.printTransaction(transaction);
        }
    }
}