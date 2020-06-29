package com.ironhack.Bank.security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    private int roleId;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AccountHolderRole> userRoles = new HashSet<>();

    public Role() {

    }

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AccountHolderRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<AccountHolderRole> userRoles) {
        this.userRoles = userRoles;
    }
}