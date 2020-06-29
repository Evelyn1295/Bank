package com.ironhack.Bank.repository.model;


import com.ironhack.Bank.model.Checking;
import com.ironhack.Bank.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    CreditCard findByUsername(String username);
}
