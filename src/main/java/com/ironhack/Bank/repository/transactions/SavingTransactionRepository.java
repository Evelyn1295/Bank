package com.ironhack.Bank.repository.transactions;

import com.ironhack.Bank.model.transactions.SavingsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingTransactionRepository extends JpaRepository<SavingsTransaction,Integer> {
    List<SavingsTransaction> findAll();
}
