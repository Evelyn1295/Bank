package com.ironhack.Bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Bank.Exceptions.MinBalanceNotValid;
import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.transactions.SavingsTransaction;
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
public class Savings extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Money minimumBalance = new Money(new BigDecimal("1000"));
    private BigDecimal interestRate = new BigDecimal("0.0025");

    @OneToMany(mappedBy = "savings", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SavingsTransaction> savingsTransactionList;

    public Savings() {
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public Savings(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
    }
    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate, Money minimumBalance) {
        super(balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,Money minimumBalance) {
        super(balance, primaryOwner, secondaryOwner);
        setMinimumBalance(minimumBalance);
    }

    public Savings(Money balance, AccountHolder primaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setInterestRate(interestRate);
    }

    public Savings(Money balance, AccountHolder primaryOwner, BigDecimal interestRate, Money minimumBalance) {
        super(balance, primaryOwner);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
    }

    public Savings(Money balance, AccountHolder primaryOwner,Money minimumBalance) {
        super(balance, primaryOwner);
        setMinimumBalance(minimumBalance);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SavingsTransaction> getSavingsTransactionList() {
        return savingsTransactionList;
    }

    public void setSavingsTransactionList(List<SavingsTransaction> savingsTransactionList) {
        this.savingsTransactionList = savingsTransactionList;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        BigDecimal maxInterestRate = new BigDecimal("0.5");
        if(interestRate.compareTo(maxInterestRate) == -1){
            this.interestRate=interestRate;
        }
        this.interestRate =maxInterestRate;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {

        BigDecimal lastMinBalance = new BigDecimal("100");
        if(minimumBalance.getAmount().compareTo(lastMinBalance)==-1){
            throw new MinBalanceNotValid("This minimum balance isn't correct");
        }
        this.minimumBalance = minimumBalance;
    }

    /**

    public Money calculateInterestRateSaving() {
        LocalDate currentDate = LocalDate.now();

        if(!this.addInterest && Period.between(currentDate,createdAt).getYears() % 1 == 0){
            this.addInterest=true;
            getBalance().increaseAmount(getBalance().getAmount().multiply(interestRate));
            return new Money(getBalance().increaseAmount(getBalance().getAmount().multiply(interestRate)));
        }
        throw new InterestAddException("Interest was added");
    }
**/


}
