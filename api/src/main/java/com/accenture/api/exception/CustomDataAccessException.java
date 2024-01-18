package com.accenture.api.exception;

public class CustomDataAccessException extends RuntimeException {
    public CustomDataAccessException(String message) {
        super(message);
    }
}
