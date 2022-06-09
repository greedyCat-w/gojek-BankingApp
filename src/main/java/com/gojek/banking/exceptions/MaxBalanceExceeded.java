package com.gojek.banking.exceptions;

public class MaxBalanceExceeded extends RuntimeException{

    public MaxBalanceExceeded(double amount){
        super(String.valueOf(amount));
    }
}
