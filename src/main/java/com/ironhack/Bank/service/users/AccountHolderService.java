package com.ironhack.Bank.service.users;

import com.ironhack.Bank.model.users.AccountHolder;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountHolderService {

    AccountHolder findByUsername(String username);

    AccountHolder findByMailingAddress(String email);

    boolean checkAccountHolderExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void save(AccountHolder accountholder);

    AccountHolder createAccountHolder(AccountHolder accountholder);

    AccountHolder saveAccountHolder(AccountHolder accountholder);

    List<AccountHolder> findAccountHolderList();

    void enableAccountHolder(String username);

    void disableAccountHolder(String username);

}