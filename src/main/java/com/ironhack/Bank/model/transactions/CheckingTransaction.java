package com.ironhack.Bank.model.transactions;

import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.Checking;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class CheckingTransaction extends Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "checking_id")
    private Checking checking;


    public CheckingTransaction() {
    }

    public CheckingTransaction(Date date, String status, Money amount, Money availableBalance, Checking checking) {
        super(date, status, amount, availableBalance);
        this.checking = checking;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Checking getChecking() {
        return checking;
    }

    public void setChecking(Checking checking) {
        this.checking = checking;
    }
}
