package com.ironhack.Bank.Exceptions;

public class MinInterestRate extends RuntimeException{

    public MinInterestRate(String message) {
        super(message);
    }
}
