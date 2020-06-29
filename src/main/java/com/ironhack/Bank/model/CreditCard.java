package com.ironhack.Bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Bank.Exceptions.MaxCreditLimitException;
import com.ironhack.Bank.Exceptions.MinInterestRate;
import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.transactions.CreditCardTransaction;
import com.ironhack.Bank.model.users.AccountHolder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class CreditCard extends Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal interestRate = new BigDecimal("0.2");
    private Money creditLimit = new Money(new BigDecimal("100"));

    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CreditCardTransaction> creditCardTransactionList;

    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setInterestRate(interestRate);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner);
        setCreditLimit(creditLimit);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner,Money creditLimit) {
        super(balance, primaryOwner);
        setCreditLimit(creditLimit);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        BigDecimal lastInterestRate = new BigDecimal("0.1");
        if (interestRate.compareTo(lastInterestRate) == -1) {
            throw new MinInterestRate("Your interestRate is less than 1% Change it!");
        }

        this.interestRate = interestRate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        BigDecimal lastCreditLimit = new BigDecimal("100000");
        if (creditLimit.getAmount().compareTo(lastCreditLimit) == 1) {
            throw new MaxCreditLimitException("Your limit card must be less 100000");
        }
        this.creditLimit = creditLimit;
    }

    public List<CreditCardTransaction> getCreditCardTransactionList() {
        return creditCardTransactionList;
    }

    public void setCreditCardTransactionList(List<CreditCardTransaction> creditCardTransactionList) {
        this.creditCardTransactionList = creditCardTransactionList;
    }

}