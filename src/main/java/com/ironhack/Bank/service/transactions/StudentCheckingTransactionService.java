package com.ironhack.Bank.service.transactions;

import com.ironhack.Bank.enums.StatusAccount;
import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.StudentChecking;
import com.ironhack.Bank.model.transactions.StudentCheckingTransaction;
import com.ironhack.Bank.model.users.AccountHolder;
import com.ironhack.Bank.repository.model.StudentCheckingRepository;
import com.ironhack.Bank.repository.transactions.StudentCheckingTransactionRepository;
import com.ironhack.Bank.service.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentCheckingTransactionService {

    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private StudentCheckingTransactionRepository studentCheckingTransactionRepository;

    public List<StudentCheckingTransaction> findCheckingTransactionList(String username) {
        AccountHolder accountHolder = accountHolderService.findByUsername(username);
        List<StudentCheckingTransaction> savingsTransactionList = accountHolder.getStudentChecking().getStudentTransactionList();
        return savingsTransactionList;
    }

    public void saveStudentCheckingDepositTransaction(StudentCheckingTransaction studentCheckingTransaction) {
        studentCheckingTransactionRepository.save(studentCheckingTransaction);
    }

    public void betweenCheckingTransfer(Money amount, StudentChecking studentAccount, StudentChecking studentAccount2) throws Exception {
        if (!studentAccount.getId().equals(studentAccount2.getId()) &&
                !studentAccount.getStatusAccount().equals(StatusAccount.FROZEN) &&
                !studentAccount2.getStatusAccount().equals(StatusAccount.FROZEN)) {
            studentAccount.setBalance(new Money(studentAccount.getBalance().decreaseAmount(amount)));
            studentAccount2.setBalance(new Money (studentAccount2.getBalance().increaseAmount(amount)));
            studentCheckingRepository.save(studentAccount);
            studentCheckingRepository.save(studentAccount2);

            Date date = new Date();

            StudentCheckingTransaction studentCheckingTransaction =
                    new StudentCheckingTransaction(date,"Finished",amount, studentAccount.getBalance(), studentAccount);
            studentCheckingTransactionRepository.save(studentCheckingTransaction);
        } else {
            throw new Exception("Invalid Transfer");
        }
    }
}
