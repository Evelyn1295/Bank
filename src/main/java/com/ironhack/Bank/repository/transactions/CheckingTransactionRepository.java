package com.ironhack.Bank.repository.transactions;

import com.ironhack.Bank.model.transactions.CheckingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckingTransactionRepository extends JpaRepository<CheckingTransaction,Integer> {

    List<CheckingTransaction> findAll();
}
