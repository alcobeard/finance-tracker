package finance.tracker.dto;
import java.time.LocalDateTime;


public class Transaction {
    private int id;
    private double amount;
    private LocalDateTime date;
    private TransactionType type;
    private Category category;
    private String description;

    public Transaction(int id, double amount, LocalDateTime date, TransactionType type, Category category, String description) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public TransactionType getType() {
        return type;
    }
    public Category getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }

    public double getSignedAmount() {
        if (type == TransactionType.INCOME) {
            return amount;
        }
        return -amount;
    }
}
