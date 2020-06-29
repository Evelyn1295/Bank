package com.ironhack.Bank.repository.transactions;

import com.ironhack.Bank.model.transactions.CreditCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction,Integer> {
    List<CreditCardTransaction> findAll();
}
