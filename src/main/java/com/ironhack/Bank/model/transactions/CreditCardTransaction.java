package com.ironhack.Bank.model.transactions;

import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.Checking;
import com.ironhack.Bank.model.CreditCard;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class CreditCardTransaction extends Transaction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;

    public CreditCardTransaction() {}

    public CreditCardTransaction(Date date, String status, Money amount, Money availableBalance, CreditCard creditCard) {
        super(date, status, amount, availableBalance);
        this.creditCard = creditCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
