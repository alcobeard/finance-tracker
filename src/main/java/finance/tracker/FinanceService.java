package finance.tracker;
import finance.tracker.dto.Transaction;
import finance.tracker.dto.TransactionType;
import finance.tracker.dto.Category;

import java.lang.classfile.attribute.SourceFileAttribute;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


/**
 * @author Aleksandr Bagdasarov
 * 2026-04
 */

public class FinanceService {
    static int prices = 0;

    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // <-- это флаг
        while (running) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> transactionAdd(scanner, transactions);
                    case 2 -> transactionList(scanner, transactions);
                    case 3 -> showBalance(transactions);
                    case 4 -> removeTransaction(scanner, transactions);
                    case 5 -> {
                        exit();
                        return;
                    }
                    case 9 -> transactions.addAll(trashGenerator(10));
                    default -> {
                        System.out.println("------------------------------------------");
                        System.out.println("Выберете пункт меню!");
                        System.out.println("------------------------------------------");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("------------------------------------------");
                System.out.println("Ошибка ввода!");
                System.out.println("------------------------------------------");
                System.out.println("Выберете пункт меню!");
                System.out.println("------------------------------------------");
            }
        }
    }

    /**
     * П.П   Описание функционала:                  Методы:
     * Отобразить - Главное меню:             showMenu
     * 1.    Добавить транзакцию                    transactionAdd
     * 2.    Отобразить список операций             transactionList
     * 2.1.  Отобразить список всех операций        showAllTransactions
     * 2.1.  Отобразить список доходов              showIncome
     * 2.2.  Отобразить список расходов             showExpense
     * 2.3.  Фильтрация по категориям               filterByCategory
     * 2.3.1 Отображение списка с категорией        showByCategory
     * 2.4.  Отображает список операций по дате     showTransactionsByDate
     * 2.5.  Отображает список операций за период   showTransactionsByDateRange
     * 2.6.  Поиск операций по описанию             showTransactionsByDescription
     * 2.7.  Сортировка операций по дате            sortTransactionsByDate
     * 2.8.  Сортировка операций по сумме           sortTransactionByAmount
     * 2.9   Показать расходы по категориям         showExpensesByCategory
     * 2.10. Найти самую большую трату              showMaxExpense
     * 2.11. Показать расходы за месяц              showExpensesByMonth
     * 2.12. Расчитать средний расход               showAvgExpenses
     * 2.13. Отобразить список уникальных категорий showUniqueCategories
     * 3.    Расчитать прибыль                      showBalance
     * 4.    Удалить операцию по ID                 removeTransaction
     * 5.    Выход                                  exit
     * <p>
     * <p>
     *      Доп. функции
     * <p>
     * 1.   Принимает и форматирует дату            inputDate
     * 2.   Вывод таблицы                           printTransactionsTable
     * 3.   Выбрать категорию                       choiceCategory
     * 4.   Проверить таблицу на пустоту            checkEmpty
     * <p>
     * Добавить:
     * Подумать над выходом из вопросов.
     */

    public static void showMenu() {
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

    public static void transactionAdd(Scanner scanner, ArrayList<Transaction> transactions) {
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
                    Category category = choiceCategory(scanner);
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

    public static void transactionList(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Сделайте выбор:");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Отобразить список всех операций");
        System.out.println("2. Отобразить список доходов");
        System.out.println("3. Отобразить список расходов");
        System.out.println("4. Фильтрация по категориям");
        System.out.println("5. Отобразить список операций за конкретную дату");
        System.out.println("6. Отобразить список операций за период");
        System.out.println("7. Поиск транзакций по описанию");
        System.out.println("8. Отобразить список операции с сортировкой по дате");
        System.out.println("9. Отобразить список операции с сортировкой по сумме");
        System.out.println("10. Отобразить расходы по категориям");
        System.out.println("11. Отобразить самую большую трату");
        System.out.println("12. Отобразить траты за месяц");
        System.out.println("13. Отобразить средний расход");
        System.out.println("14. Отобразить список уникальных категорий");
        System.out.println("15. Вернуться в главное меню");
        System.out.println("--------------------------------------------------------");
        System.out.print("Ваш выбор № ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> showAllTransactions(transactions);
            case "2" -> showIncome(transactions);
            case "3" -> showExpense(transactions);
            case "4" -> filterByCategory(scanner, transactions);
            case "5" -> showTransactionsByDate(scanner, transactions);
            case "6" -> showTransactionsByDateRange(scanner, transactions);
            case "7" -> showTransactionsByDescription(scanner, transactions);
            case "8" -> sortTransactionsByDate(scanner, transactions);
            case "9" -> sortTransactionsByAmount(scanner, transactions);
            case "10" -> showExpensesByCategory(scanner, transactions);
            case "11" -> showMaxExpense(transactions);
            case "12" -> showExpensesByMonth (scanner, transactions);
            case "13" -> showAvgExpenses (transactions);
            case "14" -> showUniqueCategories(transactions);
            case "15" -> {
                return;
            }
            default -> {
                System.out.println("--------------------------------------------------------");
                System.out.println("Выберете пункт из списка!");
                System.out.println("--------------------------------------------------------");
            }
        }
    }

    public static void showAllTransactions(ArrayList<Transaction> transactions) {
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

    public static void showIncome(ArrayList<Transaction> transactions) {
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

    public static void showExpense(ArrayList<Transaction> transactions) {
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

    public static void showBalance(ArrayList<Transaction> transactions) {
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

    public static void removeTransaction(Scanner scanner, ArrayList<Transaction> transactions) {
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

    public static void exit() {
        System.out.println("--------------------------------------------------------");
        System.out.println("                     До свидания!                       ");
        System.out.println("--------------------------------------------------------");
    }

    public static void filterByCategory(Scanner scanner, ArrayList<Transaction> transactions) {
        printCategoriesMenu();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> showByCategory(transactions, Category.зарплата);
            case "2" -> showByCategory(transactions, Category.еда);
            case "3" -> showByCategory(transactions, Category.транспорт);
            case "4" -> showByCategory(transactions, Category.развлечения);
            case "5" -> showByCategory(transactions, Category.здоровье);
            case "6" -> showByCategory(transactions, Category.другое);
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

    public static void showByCategory(ArrayList<Transaction> transactions, Category category) {
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

    public static LocalDate inputDate(Scanner scanner) {
        LocalDate date;
        while (true) {
            System.out.print("Введите дату (дд-мм-гггг или дд.мм.гггг): ");
            String input = scanner.nextLine();

            if (input.matches("\\d{2}-\\d{2}-\\d{4}") || input.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                String[] parts;
                if (input.contains("-")) {
                    parts = input.split("-");
                } else {
                    parts = input.split("\\.");
                }

                String isoDate = parts[2] + "-" + parts[1] + "-" + parts[0];

                try {
                    date = LocalDate.parse(isoDate);
                    return date;
                } catch (DateTimeParseException e) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Ошибка! Такой даты не существует");
                    System.out.println("--------------------------------------------------------");
                }
            } else {
                System.out.println("Ошибка! Используйте формат дд-мм-гггг или дд.мм.гггг");
            }
        }
    }

    public static void showTransactionsByDate(Scanner scanner, ArrayList<Transaction> transactions) {
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

    public static void showTransactionsByDateRange(Scanner scanner, ArrayList<Transaction> transactions) {
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

    public static ArrayList<Transaction> trashGenerator(int count) {
        ArrayList<Transaction> newList = new ArrayList<>();
        TransactionType[] types = TransactionType.values();
        Category[] categories = Category.values();
        for (int i = 1; i <= count; i++) {
            String discription = new String("Тест");
            TransactionType randomType = types[ThreadLocalRandom.current().nextInt(types.length)];
            int randomAmount = ThreadLocalRandom.current().nextInt(1, 100000);
            long randomDays = ThreadLocalRandom.current().nextLong(-365, 1);
            LocalDate randomDate = LocalDate.now().plusDays(randomDays);
            Category randomCategory = categories[ThreadLocalRandom.current().nextInt(categories.length)];
            Transaction transaction = new Transaction(i, randomType, randomAmount, randomDate, randomCategory, discription);
            if (randomType == TransactionType.доход) {
                prices = prices + randomAmount;
            } else {
                prices = prices - randomAmount;
            }
            newList.add(transaction);
        }
        return newList;
    }

    public static void showTransactionsByDescription(Scanner scanner, ArrayList<Transaction> transactions) {
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

    public static void sortTransactionsByDate (Scanner scanner, ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
            System.out.println("1. Отсортировать по возрастанию");
            System.out.println("2. Отсортировать по убыванию");
            System.out.println("3. Вернуться в главное меню");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> transactions.sort(Comparator.comparing(Transaction::getDate));
                case "2" -> transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
                case "3" -> {
                    showMenu();
                    return;
                }
                default -> {
                    System.out.println("------------------------------------------");
                    System.out.println("Выберете пункт меню!");
                    System.out.println("------------------------------------------");
                }
            }
            printTransactionsTable(transactions);
    }

    public static void printTransactionsTable (ArrayList<Transaction> transactions) {
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

    public static void sortTransactionsByAmount (Scanner scanner, ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        System.out.println("1. Отсортировать по возрастанию");
        System.out.println("2. Отсортировать по убыванию");
        System.out.println("3. Вернуться в главное меню");
        String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> transactions.sort(Comparator.comparing(Transaction::getAmount));
                case "2" -> transactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
                case "3" -> {
                    showMenu();
                    return;
                }
                default -> {
                    System.out.println("------------------------------------------");
                    System.out.println("Выберете пункт меню!");
                    System.out.println("------------------------------------------");
                }
            }
            printTransactionsTable(transactions);
        }

    public static void showExpensesByCategory (Scanner scanner, ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        while (true) {
            printCategoriesMenu();
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
                    showMenu();
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

    public static void printCategoriesMenu () {
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

    public static void showMaxExpense (ArrayList<Transaction> transactions) {
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

    public static void showExpensesByMonth (Scanner scanner, ArrayList<Transaction> transactions) {
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

    public static void showAvgExpenses (ArrayList<Transaction> transactions) {
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

    public static void showUniqueCategories (ArrayList<Transaction> transactions) {
        checkEmpty(transactions);
        HashSet <Category> uniqueCategories = new HashSet<>();
        for (int i = 0; i < transactions.size(); i++){
            uniqueCategories.add(transactions.get(i).getCategory());
        }
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Список уникальных категорий: " + uniqueCategories);
        System.out.println("-------------------------------------------------------------------------------");
    }

    public static Category choiceCategory (Scanner scanner) {
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

    public static void checkEmpty(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Список операций пуст!");
            System.out.println("-------------------------------------------------------------------------------");
            return;
        }
    }
}