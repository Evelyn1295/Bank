package com.ironhack.Bank.security;

import com.ironhack.Bank.model.users.AccountHolder;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class AccountHolderRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


    public AccountHolderRole(AccountHolder accountHolder, Role role) {
        this.accountHolder = accountHolder;
        this.role = role;
    }

    public AccountHolderRole() {
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public AccountHolder getUser() {
        return accountHolder;
    }

    public void setUser(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}