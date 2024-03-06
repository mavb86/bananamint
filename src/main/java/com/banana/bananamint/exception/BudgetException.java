package com.banana.bananamint.exception;

public class BudgetException extends RuntimeException{
    public BudgetException() {
    }

    public BudgetException(String message) {
        super(message);
    }
}
