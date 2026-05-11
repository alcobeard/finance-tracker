package finance.tracker;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FinanceService {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<HashMap<String, String>> transactions = new ArrayList<>();

        boolean running = true;

        while (running) {
            showMenu();

            String command = scanner.nextLine();

            if (command.equals("1")) {
                System.out.println("Выберите тип операции");

                System.out.println("1. Доход");

                System.out.println("2. Расход");

                System.out.println("3. Назад");

                String typeCommand = scanner.nextLine();

                if (typeCommand.equals("1")) {
                    System.out.println("Доход");

                    System.out.println("Введите категорию");
                    String category = scanner.nextLine();

                    System.out.println("Введите сумму");
                    String amount = scanner.nextLine();

                    System.out.println("Введите дату");
                    String date = scanner.nextLine();

                    HashMap<String, String> transaction = new HashMap<>();

                    transaction.put("Тип", "Доход");
                    transaction.put("Категория", category);
                    transaction.put("Сумма", amount);
                    transaction.put("Дата", date);

                    transactions.add(transaction);

                    System.out.println("Доход сохранён");

                }
                if (typeCommand.equals("2")) {
                    System.out.println("Расход");

                    System.out.println("Категория");
                    String category = scanner.nextLine();

                    System.out.println("Сумма");
                    String amount = scanner.nextLine();

                    System.out.println("Дата");
                    String date = scanner.nextLine();

                    HashMap<String, String> transaction = new HashMap<>();
                    transaction.put("Тип", "Расход");
                    transaction.put("Категория", category);
                    transaction.put("Сумма", amount);
                    transaction.put("Дата", date);

                    transactions.add(transaction);

                    System.out.println("Расход сохранён");
                }
                if (typeCommand.equals("3")) {
                    System.out.println("Возврат в главное меню");
                }

            }
            if (command.equals("2")) {
                if (transactions.isEmpty()) {
                    System.out.println("Пусто");
                } else {
                    for (HashMap<String, String> transaction : transactions) {
                        System.out.println("Тип: " + transaction.get("Тип"));
                        System.out.println("Категория: " + transaction.get("Категория"));
                        System.out.println("Сумма: " + transaction.get("Сумма"));
                        System.out.println("Дата: " + transaction.get("Дата"));
                    }
                }
            }
            if (command.equals("3")) {
                int balance = 0;

                for (HashMap<String, String> transaction : transactions) {
                    int amountValue = Integer.parseInt(transaction.get("Сумма"));

                    if (transaction.get("Тип").equals("Доход")) {
                        balance = balance + amountValue;
                    }

                    if (transaction.get("Тип").equals("Расход")) {
                        balance = balance - amountValue;
                    }
                }

                System.out.println("Баланс: " + balance);
            }
            if (command.equals("5")) {
                running = false;
                System.out.println("Выход из программы");
            }
        }
    }

    public static void showMenu() {
        System.out.println("Welcome to Finance Tracker");
        System.out.println("1. Add transaction");
        System.out.println("2. List transaction");
        System.out.println("3. Show balance");
        System.out.println("4. Delete transaction by id");
        System.out.println("5. Exit");
    }
}