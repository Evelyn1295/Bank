package com.ironhack.Bank.model.transactions;

import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.StudentChecking;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class StudentCheckingTransaction extends Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_checking_id")
    private StudentChecking studentCheckingCard;

    public StudentCheckingTransaction() {
    }

    public StudentCheckingTransaction(Date date, String status, Money amount, Money availableBalance, StudentChecking studentCheckingCard) {
        super(date, status, amount, availableBalance);
        this.studentCheckingCard = studentCheckingCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StudentChecking getStudentCheckingCard() {
        return studentCheckingCard;
    }

    public void setStudentCheckingCard(StudentChecking studentCheckingCard) {
        this.studentCheckingCard = studentCheckingCard;
    }
}
