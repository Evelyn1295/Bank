package com.ironhack.Bank.model.transactions;

import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.Savings;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class SavingsTransaction extends Transaction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "savings_id")
    private Savings savings;

    public SavingsTransaction() {
    }

    public SavingsTransaction(Date date, String status, Money amount, Money availableBalance, Savings savings) {
        super(date, status, amount, availableBalance);
        this.savings = savings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Savings getSavings() {
        return savings;
    }

    public void setSavings(Savings savings) {
        this.savings = savings;
    }
}
