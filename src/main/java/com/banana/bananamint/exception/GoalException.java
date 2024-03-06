package com.banana.bananamint.exception;

public class GoalException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public GoalException() {
    }

    public GoalException(String message) {
        super(message);
    }
}
