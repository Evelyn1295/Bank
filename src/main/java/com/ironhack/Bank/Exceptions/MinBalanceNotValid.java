package com.ironhack.Bank.Exceptions;

public class MinBalanceNotValid extends RuntimeException{

    public MinBalanceNotValid(String message) {
        super(message);
    }
}
