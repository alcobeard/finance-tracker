package finance.tracker;
import finance.tracker.Util.Utils;
import finance.tracker.dto.Transaction;
import finance.tracker.service.*;
import jdk.jshell.execution.Util;

import java.util.*;


/**
 * @author Aleksandr Bagdasarov
 * 2026-04
 */

public class BudgetApp {
    public static int prices = 0;

    public static void main(String[] args) {
        TransactionService ts = new TransactionService();
        DisplayService ds = new DisplayService();
        StatisticsService ss = new StatisticsService();
        FileService fs = new FileService();
        MenuService ms = new MenuService();
        Utils util = new Utils();
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions = fs.loadTransactions ();
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // <-- это флаг
        while (running) {
            ms.showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> ts.transactionAdd(scanner, transactions, ms);
                    case 2 -> ms.transactionList(scanner, transactions, ts, ds, ss);
                    case 3 -> ss.showBalance(transactions);
                    case 4 -> ts.removeTransaction(scanner, transactions);
                    case 5 -> {
                        fs.saveTransactionsToFile(transactions);
                        exit();
                        return;
                    }
                    case 9 -> transactions.addAll(util.trashGenerator(10, transactions));
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

    public static void exit() {
        System.out.println("--------------------------------------------------------");
        System.out.println("                     До свидания!                       ");
        System.out.println("--------------------------------------------------------");
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
 *
 * TransactionService — работает с данными
 * DisplayService — нужен для вывода (
 * StatisticsService — нужен для расчётов
 * FileService — загрузка/сохранение
 * MenuService — меню
 * BudgetApp — точка входа
 *
 *
 */