package com.ironhack.Bank.service.transactions;

import com.ironhack.Bank.enums.StatusAccount;
import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.Checking;
import com.ironhack.Bank.model.StudentChecking;
import com.ironhack.Bank.model.transactions.CheckingTransaction;
import com.ironhack.Bank.model.transactions.StudentCheckingTransaction;
import com.ironhack.Bank.model.users.AccountHolder;
import com.ironhack.Bank.repository.model.CheckingRepository;
import com.ironhack.Bank.repository.model.StudentCheckingRepository;
import com.ironhack.Bank.repository.transactions.CheckingTransactionRepository;
import com.ironhack.Bank.repository.transactions.StudentCheckingTransactionRepository;
import com.ironhack.Bank.service.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : online-banking
 * AccountHolder: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/08/18
 * Time: 06.21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CheckingTransactionService

{

    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private CheckingTransactionRepository checkingTransactionRepository;

    @Autowired
    private StudentCheckingTransactionRepository studentCheckingTransactionRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    public List<CheckingTransaction> findCheckingTransactionList(String username) {
        AccountHolder accountHolder = accountHolderService.findByUsername(username);
        List<CheckingTransaction> primaryTransactionList = accountHolder.getChecking().getCheckingTransactionList();
        return primaryTransactionList;
    }

    public List<StudentCheckingTransaction> findSavingsTransactionList(String username) {
        AccountHolder accountHolder = accountHolderService.findByUsername(username);
        List<StudentCheckingTransaction> studentCheckingTransactionsList = accountHolder.getStudentChecking().getStudentTransactionList();

        return studentCheckingTransactionsList;
    }

    public void saveCheckingDepositTransaction(CheckingTransaction checkingTransaction) {
        checkingTransactionRepository.save(checkingTransaction);
    }

    public void saveStudentCheckingDepositTransaction(StudentCheckingTransaction studentCheckingTransaction) {
        studentCheckingTransactionRepository.save(studentCheckingTransaction);
    }

    public void saveCheckingWithdrawTransaction(CheckingTransaction checkingTransaction) {
        checkingTransactionRepository.save(checkingTransaction);
    }

    public void saveStudentCheckingWithdrawTransaction(StudentCheckingTransaction studentCheckingTransaction) {
        studentCheckingTransactionRepository.save(studentCheckingTransaction);
    }

    public void betweenCheckingTransfer(Money amount, Checking checkingAccount, Checking checkingAccount2) throws Exception {
        if (!checkingAccount.getId().equals(checkingAccount2.getId()) && 
                !checkingAccount.getStatusAccount().equals(StatusAccount.FROZEN) && 
                !checkingAccount2.getStatusAccount().equals(StatusAccount.FROZEN)) {
            checkingAccount.setBalance(new Money(checkingAccount.getBalance().decreaseAmount(amount)));
            checkingAccount2.setBalance(new Money (checkingAccount2.getBalance().increaseAmount(amount)));
            checkingRepository.save(checkingAccount);
            checkingRepository.save(checkingAccount2);

            Date date = new Date();

            CheckingTransaction checkingTransaction =
                    new CheckingTransaction(date,"Finished",amount, checkingAccount.getBalance(), checkingAccount);
            checkingTransactionRepository.save(checkingTransaction);
        } else {
            throw new Exception("Invalid Transfer");
        }
    }


    public void toSomeoneElseTransfer(Money amount, Checking checkingAccount, StudentChecking studentAccount) {
        if (!checkingAccount.getStatusAccount().equals(StatusAccount.FROZEN) && !studentAccount.getStatusAccount().equals(StatusAccount.FROZEN)){
            checkingAccount.setBalance(new Money(checkingAccount.getBalance().decreaseAmount(amount)));
            studentAccount.setBalance(new Money (studentAccount.getBalance().increaseAmount(amount)));
            checkingRepository.save(checkingAccount);
            studentCheckingRepository.save(studentAccount);

            Date date = new Date();

            CheckingTransaction checkingTransaction = new CheckingTransaction(date,"Finished",amount, checkingAccount.getBalance(), checkingAccount);
            checkingTransactionRepository.save(checkingTransaction);
        }
    }

}