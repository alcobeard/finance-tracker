package finance.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author Aleksandr Bagdasarov
 * 2026-05
 */

public class FinanceService {
    public static void main(String[] args) {
        showMenu();
        ArrayList<HashMap<String, String>> transactions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running){
            try {
                int choice = Integer.parseInt(scanner.nextLine());              // сначала был while (choice != 5), но потом из за проблем с валидацей пришлось отказаться
                switch (choice) {                                                   // добавил case 5:
                    case 1:
                        while (true) {
                            try {
                                HashMap<String, String> transactionAdd = new HashMap<>();
                                System.out.println("------------------------------------------");
                                System.out.println("==Трекер бюджета | Добавление транзакции==");
                                System.out.println("------------------------------------------");
                                System.out.print("Введите сумму вашего дохода или расхода: ");
                                int amount = Integer.parseInt(scanner.nextLine());
                                if (amount > 0) {
                                    String type = new String(); //scanner.nextLine();
                                    while (true) {
                                        System.out.println("------------------------------------------");
                                        System.out.print("Введите тип транзакции (доход/расход): ");
                                        String input = scanner.nextLine();
                                        if (input.equalsIgnoreCase("доход") || input.equalsIgnoreCase("расход")) {
                                            type = input.toLowerCase();
                                            break;
                                        } else {
                                            System.out.println("------------------------------------------");
                                            System.out.println("Ошибка ввода!");
                                        }
                                    }
                                    String date = new String();
                                    while (true) {
                                        System.out.println("------------------------------------------");
                                        System.out.print("Введите дату транзакции (формат: дд-мм-гггг / дд.мм.гггг): ");
                                        String input = scanner.nextLine();
                                        if (input.matches("\\d{2}-\\d{2}-\\d{4}") || (input.matches("\\d{2}.\\d{2}.\\d{4}"))) {
                                            date = input;
                                            break;
                                        } else {
                                            System.out.println("------------------------------------------");
                                            System.out.println("Ошибка ввода!");
                                        }
                                    }
                                    int newId = Integer.parseInt(String.valueOf(transactions.size() + 1)); // тут я хотел изначально сделать пиздец какие проверки написав циклы, но придумал такое элегантное решение (идея моя, помог deepseek)
                                    transactionAdd.put("id", String.valueOf(newId));
                                    transactionAdd.put("type", type);
                                    transactionAdd.put("amount", String.valueOf(amount));
                                    transactionAdd.put("date", date);
                                    transactions.add(transactionAdd);
                                    System.out.println("------------------------------------------");
                                    System.out.println("       Транзакция успешно добавлена!      ");
                                    System.out.println("------------------------------------------");
                                    // System.out.println("Транзакция добавлена: " +transaction1.get("id") +" " +transaction1.get("type"));
                                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %n", "ID", "Сумма", "Тип", "Дата");
                                    System.out.println("|------|-----------|--------|------------|");
                                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %n", transactionAdd.get("id"), transactionAdd.get("amount"), transactionAdd.get("type"), transactionAdd.get("date"));
                                    System.out.println("------------------------------------------");
                                    showMenu();
                                    break;
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("------------------------------------------");
                                System.out.println("Ошибка ввода!");
                            }
                        }
                        break;
                    case 2:
                        if (transactions.isEmpty()) {
                            System.out.println("------------------------------------------");
                            System.out.println("Список операций пуст!");
                            System.out.println("------------------------------------------");
                        } else {
                            System.out.println("------------------------------------------");
                            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %n", "ID", "Сумма", "Тип", "Дата");
                            System.out.println("|------|-----------|--------|------------|");
                            for (int i = 0; i < transactions.size(); i++) {
                                System.out.printf("| %-4s | %-9s | %-6s | %-10s | %n", transactions.get(i).get("id"), transactions.get(i).get("amount"), transactions.get(i).get("type"), transactions.get(i).get("date"));
                                System.out.println("|------|-----------|--------|------------|");
                            }
                        }
                        showMenu();
                        break;
                    case 3:
                        System.out.println("------------------------------------------");
                        System.out.println("===  Трекер бюджета | Рассчёт прибыли  ===");
                        int prices = 0;
                        if (transactions.isEmpty()) {                          // transactions.size = 0 не работает
                            System.out.println("------------------------------------------");
                            System.out.println("Список операций пуст!");
                            System.out.println("------------------------------------------");
                        } else {
                            for (int i = 0; i < transactions.size(); i++) {
                                if (transactions.get(i).get("type").equals("доход")) {                        // (transactions.get(i).get("type") == "доход" не работает
                                    prices = prices + Integer.parseInt(transactions.get(i).get("amount"));
                                } else {
                                    prices = prices - Integer.parseInt(transactions.get(i).get("amount"));
                                }
                            }
                            System.out.println("Ваша прибыль составляет: " + prices);
                        }
                        showMenu();
                        break;
                    case 4:
                        System.out.println("------------------------------------------");
                        System.out.print("Введите ID операции: ");
                        String input = scanner.nextLine();
                        boolean found = false;
                        for (int i = 0; i < transactions.size(); i++) {                   //долго ломал голову, не знал, что if без else работает
                            if (transactions.get(i).get("id").equals(input)) {
                                transactions.remove(i);
                                found = true;
                                System.out.println("------------------------------------------");
                                System.out.println("Операция с ID: " + input +" удалена!");
                                System.out.println("------------------------------------------");
                                break;
                            }
                        }
                        if (found == false) {
                            System.out.println("------------------------------------------");
                            System.out.println("Операции с ID:" + input + " не существует!");
                            System.out.println("------------------------------------------");
                        }
                        showMenu();
                        break;
                    case 5:
                        System.out.println("------------------------------------------");
                        System.out.println("              До свидания!                ");
                        System.out.println("------------------------------------------");
                        return;
                    default:
                        System.out.println("------------------------------------------");
                        System.out.println("Выберете пункт меню!");
                        System.out.println("------------------------------------------");
                        showMenu();
                        break;
                }
            }
            catch(NumberFormatException e) {
                System.out.println("------------------------------------------");
                System.out.println("Ошибка ввода!");
                System.out.println("------------------------------------------");
                System.out.println("Выберете пункт меню!");
                System.out.println("------------------------------------------");
                showMenu();
            }
//        choice = Integer.parseInt(scanner.nextLine());
        }
    }

    public static void showMenu() {
        System.out.println("========= Трекер Вашего бюджета ==========");
        System.out.println("------------------------------------------");
        System.out.println("1. Добавить доход или расход");
        System.out.println("2. Отобразить список всех операций");
        System.out.println("3. Расчитать прибыль");
        System.out.println("4. Удалить операцию по ID");
        System.out.println("5. Выход");
        System.out.println("------------------------------------------");
        System.out.print("Пункт меню № ");
    }
}
