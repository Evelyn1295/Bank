package com.ironhack.Bank.model;

import com.ironhack.Bank.enums.StatusAccount;
import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.users.AccountHolder;

import java.math.BigDecimal;

public abstract class Account {

    private Money balance;
    private String secretKey;
    private AccountHolder primaryOwner;
    private AccountHolder secondaryOwner;
    private Money penaltyFee = new Money(new BigDecimal("40"));
    private StatusAccount statusAccount = StatusAccount.ACTIVE;

    public Account() {
    }

    public Account(Money balance,AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Account(Money balance,AccountHolder primaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public StatusAccount getStatusAccount() {
        return statusAccount;
    }

    public void setStatusAccount(StatusAccount statusAccount) {
        this.statusAccount = statusAccount;
    }
}
