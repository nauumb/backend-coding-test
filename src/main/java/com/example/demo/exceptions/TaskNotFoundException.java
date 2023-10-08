package com.example.demo.exceptions;

import java.util.NoSuchElementException;

public class TaskNotFoundException extends NoSuchElementException {
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
