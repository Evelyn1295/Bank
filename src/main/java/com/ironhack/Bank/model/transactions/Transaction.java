package com.ironhack.Bank.model.transactions;

import com.ironhack.Bank.helpers.Money;
import java.util.Date;

public abstract class Transaction {

    private Date date;
    private String status;
    private Money amount;
    private Money availableBalance;

    public Transaction() {
    }

    public Transaction(Date date, String status, Money amount, Money availableBalance) {
        this.date = date;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Money getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Money availableBalance) {
        this.availableBalance = availableBalance;
    }
}
