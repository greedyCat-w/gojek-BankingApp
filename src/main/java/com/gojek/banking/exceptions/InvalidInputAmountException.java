package com.gojek.banking.exceptions;

public class InvalidInputAmountException extends RuntimeException {

    public InvalidInputAmountException(String inputAmount) {
        super(inputAmount);
    }
}
