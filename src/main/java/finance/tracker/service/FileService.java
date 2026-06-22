package finance.tracker.service;

import finance.tracker.dto.Category;
import finance.tracker.dto.Transaction;
import finance.tracker.dto.TransactionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public ArrayList<Transaction> loadTransactions ()  {
        ArrayList<Transaction> newList = new ArrayList<>();
        Path path = Path.of("data", "transactions.csv");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                System.out.println("Файл не найден!"); // Для отладки, потом убрать
                Files.createFile(path);
                return newList;
            }
            else {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] parts = line.split(";");
                    int id = Integer.parseInt(parts[0]);
                    TransactionType type = TransactionType.valueOf(parts [1]);
                    int amount = Integer.parseInt(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3]);
                    Category category = Category.valueOf(parts[4]);
                    String discription = parts[5];
                    Transaction t = new Transaction(id, type, amount, date, category, discription);
                    newList.add(t);
                }
                return newList;
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка"); // Для отладки, потом убрать.
            return newList;
        }
    }
    public void saveTransactionsToFile (ArrayList<Transaction> transactions) {
        Path path = Path.of("data", "transactions.csv");
        ArrayList<String> lines = new ArrayList<>();
        try {
            Files.createDirectories(path.getParent());
            System.out.println("Файл сохранён!");
            for (int i = 0; i < transactions.size(); i++) {
                Transaction t = transactions.get(i);
                String line = t.getId() + ";" + t.getType() + ";" + t.getAmount() + ";" + t.getDate() + ";" + t.getCategory() + ";" + t.getDiscription();
                lines.add(line);
            }
            Files.write (path, lines);
            System.out.println("Данные сохранены в файл: " + path);
        }
        catch (IOException e) {
            System.out.println("Ошибка"); // Для отладки, потом убрать.
        }
    }
}
