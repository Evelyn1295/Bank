package com.ironhack.Bank.repository.transactions;

import com.ironhack.Bank.model.transactions.StudentCheckingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCheckingTransactionRepository extends JpaRepository<StudentCheckingTransaction,Integer> {
    List<StudentCheckingTransaction> findAll();
}
