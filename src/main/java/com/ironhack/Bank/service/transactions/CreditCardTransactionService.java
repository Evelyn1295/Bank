package com.ironhack.Bank.service.transactions;

import com.ironhack.Bank.enums.StatusAccount;
import com.ironhack.Bank.helpers.Money;
import com.ironhack.Bank.model.CreditCard;
import com.ironhack.Bank.model.transactions.CreditCardTransaction;
import com.ironhack.Bank.model.users.AccountHolder;
import com.ironhack.Bank.repository.model.CreditCardRepository;
import com.ironhack.Bank.repository.transactions.CreditCardTransactionRepository;
import com.ironhack.Bank.service.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CreditCardTransactionService {

    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardTransactionRepository creditCardTransactionRepository;
    
    public List<CreditCardTransaction> findCreditCardTransactionList(String username) {
        AccountHolder accountHolder = accountHolderService.findByUsername(username);
        List<CreditCardTransaction> creditCardTransactionList = accountHolder.getCreditCard().getCreditCardTransactionList();
        return creditCardTransactionList;
    }

    public void saveCreditCardDepositTransaction(CreditCardTransaction creditCardTransaction) {
        creditCardTransactionRepository.save(creditCardTransaction);
    }

    public void betweenCreditCardTransfer(Money amount, CreditCard creditCardAccount, CreditCard creditCardAccount2) throws Exception {
        if (!creditCardAccount.getId().equals(creditCardAccount2.getId()) &&
                !creditCardAccount.getStatusAccount().equals(StatusAccount.FROZEN) &&
                !creditCardAccount2.getStatusAccount().equals(StatusAccount.FROZEN)) {
            creditCardAccount.setBalance(new Money(creditCardAccount.getBalance().decreaseAmount(amount)));
            creditCardAccount2.setBalance(new Money (creditCardAccount2.getBalance().increaseAmount(amount)));
            creditCardRepository.save(creditCardAccount);
            creditCardRepository.save(creditCardAccount2);

            Date date = new Date();

            CreditCardTransaction checkingTransaction =
                    new CreditCardTransaction(date,"Finished",amount, creditCardAccount.getBalance(), creditCardAccount);
            creditCardTransactionRepository.save(checkingTransaction);
        } else {
            throw new Exception("Invalid Transfer");
        }
    }

}
