package finance.tracker.Util;

import finance.tracker.dto.Category;
import finance.tracker.dto.Transaction;
import finance.tracker.dto.TransactionType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static finance.tracker.BudgetApp.prices;

public class Utils {
    public ArrayList<Transaction> trashGenerator(int count, ArrayList<Transaction> transactions) {
        ArrayList<Transaction> newList = new ArrayList<>();
        TransactionType[] types = TransactionType.values();
        Category[] categories = Category.values();
        int startId = transactions.size();
        for (int i = 1; i <= count; i++) {
            String discription = new String("Тест");
            TransactionType randomType = types[ThreadLocalRandom.current().nextInt(types.length)];
            int randomAmount = ThreadLocalRandom.current().nextInt(1, 100000);
            long randomDays = ThreadLocalRandom.current().nextLong(-365, 1);
            LocalDate randomDate = LocalDate.now().plusDays(randomDays);
            Category randomCategory = categories[ThreadLocalRandom.current().nextInt(categories.length)];
            Transaction transaction = new Transaction(startId + i, randomType, randomAmount, randomDate, randomCategory, discription);
            if (randomType == TransactionType.доход) {
                prices = prices + randomAmount;
            } else {
                prices = prices - randomAmount;
            }
            newList.add(transaction);
        }
        return newList;
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
}
