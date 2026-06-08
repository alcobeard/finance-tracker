package finance.tracker.dto;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private TransactionType type;
    private int amount;
    private LocalDate date;
    private Category category;
    private String discription;

    public Transaction(int id, TransactionType type, int amount, LocalDate date, Category category, String discription) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.discription = discription;
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
    public String getDiscription () {
        return discription;
    }
}