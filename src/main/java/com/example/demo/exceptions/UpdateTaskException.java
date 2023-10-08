package com.example.demo.exceptions;

public class UpdateTaskException extends RuntimeException {
    public UpdateTaskException(String errorMessage) {
        super(errorMessage);
    }
}
