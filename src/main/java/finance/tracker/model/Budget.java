package finance.tracker.model;

import finance.tracker.dto.Transaction;

import java.util.ArrayList;

public class Budget {
    private int balance;
    private ArrayList <Transaction> transactions;

    public Budget () {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }
    public int getBalance () {
        return balance;
    }
    public ArrayList <Transaction> getTransactions () {
        return transactions;
    }
    public void setBalance (int balance) {
        this.balance = balance;
    }
    public void setTransactions (ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    public void addToBalance(int amount) {
        this.balance += amount;
    }
}
