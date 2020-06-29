package com.ironhack.Bank.repository.model;

import com.ironhack.Bank.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking,Integer> {
    Checking findByUsername(String username);

}
