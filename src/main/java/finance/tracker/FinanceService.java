package finance.tracker;

import finance.tracker.dto.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FinanceService { // это класс
    private static double balanceCommon = 0;
    private static List<Transaction> transactions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1; // транзакция получает свой id

    public static void main(String[] args) {
         //позволяет хранить объекты в Transaction
        boolean running = true;
        while (running) {
            System.out.println("Текущий баланс: " + balanceCommon); // баланс показывается сразу и считается по транзакциям
            showMenu(); // показывает меню при запуске (смотри скобки, чтобы метод работал надо чтобы класс не закрылся)
            String command = scanner.nextLine();
            switch (command) {
                case "1": {
                    addTrancastion();
                    break;
                }
                case "2": {
                    if (transactions.isEmpty()) {
                        System.out.println("Пусто");
                    } else {
                        for (Transaction transaction : transactions) {
                            System.out.println("id: " + transaction.getId());
                            System.out.println("Тип: " + transaction.getType());
                            System.out.println("Категория: " + transaction.getCategory()); // достает данные чещез get
                            System.out.println("Сумма: " + transaction.getAmount());
                            System.out.println("Дата: " + transaction.getDate());
                            System.out.println("-----");
                        }
                    }
                    break;
                }
                case "3": {
                    System.out.println("Баланс: " + calculateBalance(transactions));
                    break;
                }
                case "4": {
                    deleteTransaction();
                    break;
                }
                case "5": {
                    running = false;
                    System.out.println("Выход из программы");
                    break;
                }
                default: {
                    System.out.println("Неизвестная команда");
                    break;
                }
            }
        }
    }

    private static void deleteTransaction() {
        System.out.println("Введите id транзакции");
        int id = Integer.parseInt(scanner.nextLine());
        boolean found = false;
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);

            if (transaction.getId() == id) {
                transactions.remove(i);
                // ВОТ ТУТ БЛЯТЬ ТОЖЕ ОБНОВИТЬ БАЛАНС И ПРОВЕРИТЬ КАКАЯ СУКА ОПЕРАЦИЯ БЫЛА
                // И ИСХОДЯ ИЗ ЭТОГО УВЕЛИЧИТЬ БАЛАНС ИЛИ УМЕНЬШИТЬ
                found = true;
                System.out.println("Транзакция удалена");
                break;
            }
        }
        if (!found) {
            System.out.println("Транзакция с таким id не найдена");
        }
    }

    private static void addTrancastion() {
        System.out.println("Выберите тип операции");
        System.out.println("1. Доход");
        System.out.println("2. Расход");
        System.out.println("3. Назад");
        String typeCommand = scanner.nextLine();
        switch (typeCommand) {
            case "1": {
                System.out.println("Доход");
                System.out.println("Введите сумму");
                double amount = Double.parseDouble(scanner.nextLine());
                Category category = readCategory(scanner);
                Transaction transaction = new Transaction( //транзакция как объект
                        nextId,
                        amount,
                        LocalDateTime.now(),
                        TransactionType.INCOME, //enum для типа операции
                        category
                );
                transactions.add(transaction);
                nextId++; //увеличивает id следующей транзакции
                System.out.println("Доход сохранён");
                // ВОТ ЗДЕСЬ ОБНОВИ БАЛАНС! НЕ ЗАБУДЬ ПРО ЗНАКИ +/-
                break;
            }
            case "2": {
                System.out.println("Расход");
                System.out.println("Введите сумму");
                double amount = Double.parseDouble(scanner.nextLine());

                Category category = readCategory(scanner);

                Transaction transaction = new Transaction(
                        nextId,
                        amount,
                        LocalDateTime.now(),
                        TransactionType.EXPENSE, //enum для типа операции
                        category
                );
                transactions.add(transaction);
                nextId++;
                System.out.println("Расход сохранён");
                break;
            }
            case "3": {
                System.out.println("Возврат в главное меню");
                break;
            }
            default: {
                System.out.println("Неизвестный тип операции");
                break;
            }
        }
    }

    public static double calculateBalance(List<Transaction> transactions) { // метод баланса, тут он считается
            double balance = 0;
            for (Transaction transaction : transactions) {
                if (transaction.getType() == TransactionType.INCOME) {
                    balance = balance + transaction.getAmount();
                }
                if (transaction.getType() == TransactionType.EXPENSE) {
                    balance = balance - transaction.getAmount();
                }
            }
            return balance;
        }
        public static void showMenu() {
            System.out.println("Welcome to Finance Tracker");
            System.out.println("1. Добавить транзацию");
            System.out.println("2. Список транзакций");
            System.out.println("3. Показать баланс");
            System.out.println("4. Удалить транзакцию");
            System.out.println("5. Выход");
        }
        public static Category readCategory(Scanner scanner) {  // метод для категорий
            System.out.println("Выберите категорию");
            System.out.println("1. Зарплата");
            System.out.println("2. Еда");
            System.out.println("3. Транспорт");
            System.out.println("4. Развлечения");
            System.out.println("5. Здоровье");
            System.out.println("6. Прочее");
            String categoryCommand = scanner.nextLine();
            return switch (categoryCommand) { // enum для категорий
                case "1" -> Category.SALARY;
                case "2" -> Category.FOOD;
                case "3" -> Category.TRANSPORT;
                case "4" -> Category.ENTERTAINMENT;
                case "5" -> Category.HEALTH;
                case "6" -> Category.OTHER;
                default -> {
                    System.out.println("Неизвестная категория, выбрана Прочее");
                    yield Category.OTHER;
                }
            };
        }
    }