package com.ironhack.Bank.repository.model;


import com.ironhack.Bank.model.Checking;
import com.ironhack.Bank.model.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SavingsRepository extends JpaRepository<Savings,Integer> {
    Savings findByUsername(String username);
}
