package finance.tracker.dto;

import java.time.LocalDateTime;

/**
 * @author Aleksandr Bagdasarov
 * 2026-05
 */

public class Transaction {
    int id;
    Double amount;
    LocalDateTime date;
    TransactionType type;
    Category category;
}
