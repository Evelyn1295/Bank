package com.ironhack.Bank.repository.users;

import com.ironhack.Bank.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountHolderRepository extends JpaRepository<AccountHolder,Integer> {

    AccountHolder findByUsername(String username);

    AccountHolder findByEmail(String email);

    List<AccountHolder> findAll();
}
