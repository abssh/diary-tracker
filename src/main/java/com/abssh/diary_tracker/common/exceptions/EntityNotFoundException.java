package com.abssh.diary_tracker.common.exceptions;

public abstract class EntityNotFoundException extends RuntimeException{
    protected EntityNotFoundException(String message) {
        super(message);
    }

    protected EntityNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
