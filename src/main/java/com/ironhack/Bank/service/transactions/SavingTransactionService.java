package com.ironhack.Bank.service.transactions;

import com.ironhack.Bank.enums.StatusAccount;
import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.Savings;
import com.ironhack.Bank.model.transactions.SavingsTransaction;
import com.ironhack.Bank.model.users.AccountHolder;
import com.ironhack.Bank.repository.model.SavingsRepository;
import com.ironhack.Bank.repository.transactions.SavingTransactionRepository;
import com.ironhack.Bank.service.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SavingTransactionService {


    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private SavingTransactionRepository savingTransactionRepository;

    public List<SavingsTransaction> findSavingTransactionList(String username) {
        AccountHolder accountHolder = accountHolderService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = accountHolder.getSavings().getSavingsTransactionList();
        return savingsTransactionList;
    }

    public void saveSavingDepositTransaction(SavingsTransaction savingTransaction) {
        savingTransactionRepository.save(savingTransaction);
    }

    public void betweenCheckingTransfer(Money amount, Savings savings, Savings savings2) throws Exception {
        if (!savings.getId().equals(savings2.getId()) &&
                !savings.getStatusAccount().equals(StatusAccount.FROZEN) &&
                !savings2.getStatusAccount().equals(StatusAccount.FROZEN)) {
            savings.setBalance(new Money(savings.getBalance().decreaseAmount(amount)));
            savings2.setBalance(new Money (savings2.getBalance().increaseAmount(amount)));
            savingsRepository.save(savings);
            savingsRepository.save(savings2);

            Date date = new Date();

            SavingsTransaction savingTransaction =
                    new SavingsTransaction(date,"Finished",amount, savings.getBalance(), savings);
            savingTransactionRepository.save(savingTransaction);
        } else {
            throw new Exception("Invalid Transfer");
        }
    }
}
