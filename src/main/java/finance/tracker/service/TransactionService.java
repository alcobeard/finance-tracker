package finance.tracker.service;

import finance.tracker.dto.Category;
import finance.tracker.dto.Transaction;
import finance.tracker.dto.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static finance.tracker.BudgetApp.*;
import static finance.tracker.Util.Utils.inputDate;

public class TransactionService {
    public void transactionAdd(Scanner scanner, ArrayList<Transaction> transactions, MenuService ms) {
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
                    Category category = ms.choiceCategory(scanner);
                    if (category == null) {
                        return;
                    }
                    /**
                     Здесь используется метод inputDate, его задача принимать дату и форматировать её в iso формат
                     Основная причина дедубликация.

                     ВОПРОС. Выносить ли переменную LocalDate date, на уровень класса? Потому что она используется уже в 2 методах?
                     ВОПРОС. Не понимаю почему компилятор ругался и требовалось LocalDate date = null; ?
                     **/
                    LocalDate date = inputDate(scanner);
                    if (type == TransactionType.доход) {
                        prices = prices + amount;
                    } else {
                        prices = prices - amount;
                    }
                    int Id = transactions.size() + 1;
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Введите описание для транзакции (поле может быть пустым):");
                    System.out.print("Ввод: ");
                    String discription = scanner.nextLine();
                    Transaction newTransaction = new Transaction(Id, type, amount, date, category, discription);
                    transactions.add(newTransaction);
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("              Транзакция успешно добавлена!             ");
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                            newTransaction.getId(),
                            newTransaction.getAmount(),
                            newTransaction.getType(),
                            newTransaction.getDate(),
                            newTransaction.getCategory(),
                            newTransaction.getDiscription());
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Ошибка ввода!");
            }
        }
    }
    public void removeTransaction(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.print("Введите ID операции: ");
        String input = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < transactions.size(); i++) {                   //долго ломал голову, не знал, что if без else работает
            if (String.valueOf(transactions.get(i).getId()).equals(input) && transactions.get(i).getType() == TransactionType.доход) {
                prices = prices - transactions.get(i).getAmount();
                transactions.remove(i);
                found = true;
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Операция с ID: " + input + " удалена!");
                System.out.println("-------------------------------------------------------------------------------");
                break;
            }
            if (String.valueOf(transactions.get(i).getId()).equals(input) && transactions.get(i).getType() == TransactionType.расход) {
                prices = prices + transactions.get(i).getAmount();
                transactions.remove(i);
                found = true;
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Операция с ID: " + input + " удалена!");
                System.out.println("-------------------------------------------------------------------------------");
                break;
            }
        }
        if (found == false) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Операции с ID:" + input + " не существует!");
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
    public void sortTransactionsByDate (Scanner scanner, ArrayList<Transaction> transactions, DisplayService ds, MenuService ms) {
        checkEmpty(transactions);
        System.out.println("1. Отсортировать по возрастанию");
        System.out.println("2. Отсортировать по убыванию");
        System.out.println("3. Вернуться в главное меню");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> transactions.sort(Comparator.comparing(Transaction::getDate));
            case "2" -> transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
            case "3" -> {
                ms.showMenu();
                return;
            }
            default -> {
                System.out.println("------------------------------------------");
                System.out.println("Выберете пункт меню!");
                System.out.println("------------------------------------------");
            }
        }
        ds.printTransactionsTable(transactions);
    }
    public void sortTransactionsByAmount (Scanner scanner, ArrayList<Transaction> transactions, DisplayService ds, MenuService ms) {
        checkEmpty(transactions);
        System.out.println("1. Отсортировать по возрастанию");
        System.out.println("2. Отсортировать по убыванию");
        System.out.println("3. Вернуться в главное меню");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> transactions.sort(Comparator.comparing(Transaction::getAmount));
            case "2" -> transactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
            case "3" -> {
                ms.showMenu();
                return;
            }
            default -> {
                System.out.println("------------------------------------------");
                System.out.println("Выберете пункт меню!");
                System.out.println("------------------------------------------");
            }
        }
        ds.printTransactionsTable(transactions);
    }
}
