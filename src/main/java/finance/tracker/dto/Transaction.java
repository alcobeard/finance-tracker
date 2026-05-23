package finance.tracker.dto;

import java.time.LocalDate;

public class Transaction {
    int id;
    TransactionType type;
    int amount;
    LocalDate date;
    Category category;

    public Transaction(int id, TransactionType type, int amount, LocalDate date, Category category) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }
    public int getId () {
        return id;
    }
    public TransactionType getType () {
        return type;
    }
    public int getAmount () {
        return amount;
    }
    public LocalDate getDate () {
        return date;
    }
    public Category getCategory () {
        return category;
    }
    public void setType (TransactionType type) {
        this.type = type;
    }
}