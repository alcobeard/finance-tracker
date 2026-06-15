package finance.tracker;

import finance.tracker.dto.Category;
import finance.tracker.dto.Transaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TransactionSearchService {
    private final List<Transaction> transactions;
    private final Scanner scanner;
    public TransactionSearchService(List<Transaction> transactions, Scanner scanner) {
        this.transactions = transactions;
        this.scanner = scanner;
    }
    public void showSearchMenu() {
        boolean searchMenuRunning = true;
        while (searchMenuRunning) {
            System.out.println("Меню поиска");
            System.out.println("1. Найти операции по категории");
            System.out.println("2. Найти операции за конкретную дату");
            System.out.println("3. Найти операции за период дат");
            System.out.println("4. Найти операции по слову в описании");
            System.out.println("5. Назад");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "1": {
                    showTransactionsByCategory();
                    break;
                }
                case "2": {
                    showTransactionsByDate();
                    break;
                }
                case "3": {
                    showTransactionsByDatePeriod();
                    break;
                }
                case "4": {
                    showTransactionsByDescriptionWord();
                    break;
                }
                case "5": {
                    searchMenuRunning = false;
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
    private void showTransactionsByCategory() {
        Category category = FinanceService.readCategory(scanner);
        boolean found = false;
        for (Transaction transaction : transactions) {
            if (transaction.getCategory() == category) {
                FinanceService.printTransaction(transaction);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Операций по этой категории не найдено");
        }
    }
    private void showTransactionsByDate() {
        System.out.println("Введите дату в формате ДД.ММ.ГГГГ");
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate searchDate = LocalDate.parse(input, formatter);
        boolean found = false;
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate().toLocalDate();
            if (transactionDate.equals(searchDate)) {
                FinanceService.printTransaction(transaction);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Операций за эту дату не найдено");
        }
    }
    private void showTransactionsByDatePeriod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Введите начальную дату в формате ДД.ММ.ГГГГ");
        String startInput = scanner.nextLine();
        System.out.println("Введите конечную дату в формате ДД.ММ.ГГГГ");
        String endInput = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startInput, formatter);
        LocalDate endDate = LocalDate.parse(endInput, formatter);
        boolean found = false;
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate().toLocalDate();
            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                FinanceService.printTransaction(transaction);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Операций за этот период не найдено");
        }
    }
    private void showTransactionsByDescriptionWord() {
        System.out.println("Введите слово для поиска в описании");
        String word = scanner.nextLine().toLowerCase();
        if (word.isBlank()) {
            System.out.println("Слово для поиска не может быть пустым");
            return;
        }
        boolean found = false;
        for (Transaction transaction : transactions) {
            String description = transaction.getDescription();
            if (description != null && description.toLowerCase().contains(word)) {
                FinanceService.printTransaction(transaction);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Операций с таким описанием не найдено");
        }
    }
}