package com.ironhack.Bank.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Bank.helpers.Money;

import javax.persistence.*;

import com.ironhack.Bank.model.transactions.CheckingTransaction;
import com.ironhack.Bank.model.users.AccountHolder;


@Entity
public class Checking extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private final BigDecimal MINIMUM_BALANCE = new BigDecimal("250");
    private final BigDecimal MONTHLY_MAINTENANCE = new BigDecimal("12");

    @OneToMany(mappedBy = "checking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CheckingTransaction> checkingTransactionList;


    public Checking() {
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance,primaryOwner, secondaryOwner);

    }

    public Checking(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CheckingTransaction> getCheckingTransactionList() {
        return checkingTransactionList;
    }

    public void setCheckingTransactionList(List<CheckingTransaction> checkingTransactionList) {
        this.checkingTransactionList = checkingTransactionList;
    }






}
