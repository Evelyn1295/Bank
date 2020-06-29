package com.ironhack.Bank.Exceptions;

public class MaxCreditLimitException extends RuntimeException{

    public MaxCreditLimitException(String message) {
        super(message);
    }
}
