package com.ironhack.Bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.transactions.StudentCheckingTransaction;
import com.ironhack.Bank.model.users.AccountHolder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class StudentChecking extends Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "studentChecking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StudentCheckingTransaction> studentTransactionList;

    public StudentChecking() {
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<StudentCheckingTransaction> getStudentTransactionList() {
        return studentTransactionList;
    }

    public void setStudentTransactionList(List<StudentCheckingTransaction> studentTransactionList) {
        this.studentTransactionList = studentTransactionList;
    }
}
