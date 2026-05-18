package finance.tracker;

import java.time.LocalDate;

public class Transaction {
    int id;
    String type;
    int amount;
    LocalDate date;

    public Transaction(int id, String type, int amount, LocalDate date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
}