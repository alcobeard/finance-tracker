package finance.tracker.service;

import finance.tracker.dto.Category;
import finance.tracker.dto.TransactionType;
import finance.tracker.model.Budget;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;

import static finance.tracker.BudgetApp.checkEmpty;
import static finance.tracker.Util.Utils.inputDate;

public class SortAndFindService {
        public void showAllTransactions(Budget budget) {
            if (budget.getTransactions().isEmpty()) {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Список операций пуст!");
                System.out.println("-------------------------------------------------------------------------------");
            } else {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
                System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                for (int i = 0; i < budget.getTransactions().size(); i++) {
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                            budget.getTransactions().get(i).getId(),
                            budget.getTransactions().get(i).getAmount(),
                            budget.getTransactions().get(i).getType(),
                            budget.getTransactions().get(i).getDate(),
                            budget.getTransactions().get(i).getCategory(),
                            budget.getTransactions().get(i).getDiscription()
                    );
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                }
            }
        }
        public void showIncome(Budget budget) {
            checkEmpty(budget.getTransactions());
            System.out.println("--------------------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", "ID", "Сумма", "Тип", "Дата", "Категория");
            System.out.println("|------|-----------|--------|------------|-------------|");
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getType() == TransactionType.доход) {
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", budget.getTransactions().get(i).getId(), budget.getTransactions().get(i).getAmount(), budget.getTransactions().get(i).getType(), budget.getTransactions().get(i).getDate(), budget.getTransactions().get(i).getCategory());
                    System.out.println("|------|-----------|--------|------------|-------------|");
                }
            }
        }
        public void showExpense(Budget budget) {
            checkEmpty(budget.getTransactions());
            System.out.println("--------------------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", "ID", "Сумма", "Тип", "Дата", "Категория");
            System.out.println("|------|-----------|--------|------------|-------------|");
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getType() == TransactionType.расход) {
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", budget.getTransactions().get(i).getId(), budget.getTransactions().get(i).getAmount(), budget.getTransactions().get(i).getType(), budget.getTransactions().get(i).getDate(), budget.getTransactions().get(i).getCategory());
                    System.out.println("|------|-----------|--------|------------|-------------|");
                }
            }
        }
        public void showByCategory(Budget budget, Category category) {
            checkEmpty(budget.getTransactions());
            System.out.println("--------------------------------------------------------");
            System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", "ID", "Сумма", "Тип", "Дата", "Категория");
            System.out.println("|------|-----------|--------|------------|-------------|");
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getCategory() == category) {
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %n", budget.getTransactions().get(i).getId(), budget.getTransactions().get(i).getAmount(), budget.getTransactions().get(i).getType(), budget.getTransactions().get(i).getDate(), budget.getTransactions().get(i).getCategory());
                    System.out.println("|------|-----------|--------|------------|-------------|");
                }
            }
        }
        public void showTransactionsByDate(Scanner scanner, Budget budget) {
            checkEmpty(budget.getTransactions());
            LocalDate date = inputDate(scanner);
            boolean found = false;
            boolean printHeader = false;
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getDate().equals(date)) {
                    found = true;
                    if (printHeader == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
                        printHeader = true;
                    }
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                            budget.getTransactions().get(i).getId(),
                            budget.getTransactions().get(i).getAmount(),
                            budget.getTransactions().get(i).getType(),
                            budget.getTransactions().get(i).getDate(),
                            budget.getTransactions().get(i).getCategory(),
                            budget.getTransactions().get(i).getDiscription());
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                }
            }
            if (found == false) {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("За указанную дату ничего не найдено!");
                System.out.println("-------------------------------------------------------------------------------");
            }
        }
        public void showTransactionsByDateRange(Scanner scanner, Budget budget) {
            checkEmpty(budget.getTransactions());
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
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (!budget.getTransactions().get(i).getDate().isBefore(startDate) && !budget.getTransactions().get(i).getDate().isAfter(endDate)) {
                    found = true;
                    if (printHeader == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
                        printHeader = true;
                    }
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                            budget.getTransactions().get(i).getId(),
                            budget.getTransactions().get(i).getAmount(),
                            budget.getTransactions().get(i).getType(),
                            budget.getTransactions().get(i).getDate(),
                            budget.getTransactions().get(i).getCategory(),
                            budget.getTransactions().get(i).getDiscription());
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
        public void showTransactionsByDescription(Scanner scanner, Budget budget) {
            System.out.print("Введите искомое описание: ");
            String input = scanner.nextLine();
            if (budget.getTransactions().isEmpty()) {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Список операций пуст!");
                System.out.println("-------------------------------------------------------------------------------");
                return;
            }
            boolean found = false;
            boolean printHeader = false;
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getDiscription().toLowerCase().contains(input)) {
                    found = true;
                    if (printHeader == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
                        printHeader = true;
                    }
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                            budget.getTransactions().get(i).getId(),
                            budget.getTransactions().get(i).getAmount(),
                            budget.getTransactions().get(i).getType(),
                            budget.getTransactions().get(i).getDate(),
                            budget.getTransactions().get(i).getCategory(),
                            budget.getTransactions().get(i).getDiscription());
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
        public void printTransactionsTable (Budget budget) {
            boolean printHeader = false;
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (printHeader == false) {
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
                    printHeader = true;
                }
                System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                        budget.getTransactions().get(i).getId(),
                        budget.getTransactions().get(i).getAmount(),
                        budget.getTransactions().get(i).getType(),
                        budget.getTransactions().get(i).getDate(),
                        budget.getTransactions().get(i).getCategory(),
                        budget.getTransactions().get(i).getDiscription());
            }
            System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
        }

        public void showBalance(Budget budget) {
            System.out.println("--------------------------------------------------------");
            System.out.println("======      Трекер бюджета | Рассчёт прибыли      ======");
            System.out.println("--------------------------------------------------------");
            if (budget.getTransactions().isEmpty()) {                          // transactions.size = 0 не работает
                System.out.println("--------------------------------------------------------");
                System.out.println("Список операций пуст!");
                System.out.println("Ваша прибыль составляет: 0");
                System.out.println("--------------------------------------------------------");
            } else {
                System.out.println("Ваша прибыль составляет: " + budget.getBalance());
            }
        }
        public void showAvgExpenses (Budget budget) {
            checkEmpty(budget.getTransactions());
            int sum = 0;
            int count = 0;
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getType() == TransactionType.расход){
                    sum = sum + budget.getTransactions().get(i).getAmount();
                    count = count + 1;
                }
            }
            int avgExpense = sum / count;
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Средний расход равен: " + avgExpense);
            System.out.println("-------------------------------------------------------------------------------");
        }
        public void showExpensesByCategory (Scanner scanner, Budget budget) {
            checkEmpty(budget.getTransactions());
            while (true) {
                MenuService.printCategoriesMenu();
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> {
                        boolean found = false;
                        int expenses = 0;
                        for (int i = 0; i < budget.getTransactions().size(); i++) {
                            if (budget.getTransactions().get(i).getCategory() == Category.зарплата && budget.getTransactions().get(i).getType() == TransactionType.расход) {
                                expenses = expenses + budget.getTransactions().get(i).getAmount();
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
                        for (int i = 0; i < budget.getTransactions().size(); i++) {
                            if (budget.getTransactions().get(i).getCategory() == Category.еда && budget.getTransactions().get(i).getType() == TransactionType.расход) {
                                expenses = expenses + budget.getTransactions().get(i).getAmount();
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
                        for (int i = 0; i < budget.getTransactions().size(); i++) {
                            if (budget.getTransactions().get(i).getCategory() == Category.транспорт && budget.getTransactions().get(i).getType() == TransactionType.расход) {
                                expenses = expenses + budget.getTransactions().get(i).getAmount();
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
                        for (int i = 0; i < budget.getTransactions().size(); i++) {
                            if (budget.getTransactions().get(i).getCategory() == Category.развлечения && budget.getTransactions().get(i).getType() == TransactionType.расход) {
                                expenses = expenses + budget.getTransactions().get(i).getAmount();
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
                        for (int i = 0; i < budget.getTransactions().size(); i++) {
                            if (budget.getTransactions().get(i).getCategory() == Category.здоровье && budget.getTransactions().get(i).getType() == TransactionType.расход) {
                                expenses = expenses + budget.getTransactions().get(i).getAmount();
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
                        for (int i = 0; i < budget.getTransactions().size(); i++) {
                            if (budget.getTransactions().get(i).getCategory() == Category.другое && budget.getTransactions().get(i).getType() == TransactionType.расход) {
                                expenses = expenses + budget.getTransactions().get(i).getAmount();
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
        public void showExpensesByMonth (Scanner scanner, Budget budget) {
            checkEmpty(budget.getTransactions());
            System.out.print("Введите месяц от 1-12: ");
            int month = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Введите год: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            boolean found = false;
            boolean printHeader = false;
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getType() == TransactionType.расход && budget.getTransactions().get(i).getDate().getMonthValue() == month && budget.getTransactions().get(i).getDate().getYear() == year) {
                    found = true;
                    if (printHeader == false) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n", "ID", "Сумма", "Тип", "Дата", "Категория", "Описание");
                        printHeader = true;
                    }
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                            budget.getTransactions().get(i).getId(),
                            budget.getTransactions().get(i).getAmount(),
                            budget.getTransactions().get(i).getType(),
                            budget.getTransactions().get(i).getDate(),
                            budget.getTransactions().get(i).getCategory(),
                            budget.getTransactions().get(i).getDiscription());
                }
            }
            System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
            if (found == false) {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("За указанный период ничего не найдено");
                System.out.println("-------------------------------------------------------------------------------");
            }
        }
        public void showMaxExpense (Budget budget) {
            checkEmpty(budget.getTransactions());
            int maxExpense = 0;
            boolean found = false;
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getType() == TransactionType.расход && budget.getTransactions().get(i).getAmount() > maxExpense) {
                    maxExpense = budget.getTransactions().get(i).getAmount();
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
            for (int i = 0; i < budget.getTransactions().size(); i++) {
                if (budget.getTransactions().get(i).getType() == TransactionType.расход && budget.getTransactions().get(i).getAmount() == maxExpense) {
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                    System.out.printf("| %-4s | %-9s | %-6s | %-10s | %-11s | %-20s | %n",
                            budget.getTransactions().get(i).getId(),
                            budget.getTransactions().get(i).getAmount(),
                            budget.getTransactions().get(i).getType(),
                            budget.getTransactions().get(i).getDate(),
                            budget.getTransactions().get(i).getCategory(),
                            budget.getTransactions().get(i).getDiscription());
                    System.out.println("|------|-----------|--------|------------|-------------|----------------------|");
                }
            }
        }
        public void showUniqueCategories (Budget budget) {
            checkEmpty(budget.getTransactions());
            HashSet<Category> uniqueCategories = new HashSet<>();
            for (int i = 0; i < budget.getTransactions().size(); i++){
                uniqueCategories.add(budget.getTransactions().get(i).getCategory());
            }
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Список уникальных категорий: " + uniqueCategories);
            System.out.println("-------------------------------------------------------------------------------");
        }
}
