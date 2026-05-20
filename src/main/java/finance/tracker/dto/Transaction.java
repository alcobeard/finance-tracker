package finance.tracker.dto;


public class Transaction {
    int id;
    Double amount;
    LocalDateTime date;
    TransactionType type;
    Category category;
}
