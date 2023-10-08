package com.example.demo.exceptions;

public class CreateTaskException extends RuntimeException {
    public CreateTaskException(String errorMessage) {
        super(errorMessage);
    }

}
