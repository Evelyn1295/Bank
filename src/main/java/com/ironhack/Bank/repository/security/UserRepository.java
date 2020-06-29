package com.ironhack.Bank.repository.security;

import com.ironhack.Bank.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AccountHolder, Long> {
    /**
     * This method search user whose name attribute matches with param
     * @param username a String value
     * @return A user whose username attribute matches with username param
     */
    AccountHolder findByUsername(String username);
}