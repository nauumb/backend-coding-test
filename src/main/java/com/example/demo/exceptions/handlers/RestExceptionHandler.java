package com.example.demo.exceptions.handlers;

import com.example.demo.exceptions.CreateTaskException;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.exceptions.UpdateTaskException;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

import static java.text.MessageFormat.format;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { CreateTaskException.class, CreateTaskException.class })
    protected ResponseEntity<Object> handleCreateTaskException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = { TaskNotFoundException.class, TaskNotFoundException.class })
    protected ResponseEntity<Object> handleTaskNotFound(NoSuchElementException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { UpdateTaskException.class, UpdateTaskException.class })
    protected ResponseEntity<Object> handleUpdateTaskException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    protected ResponseEntity<Object> handleInvalidArgument(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, format("Invalid argument: [{0}]", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ConversionFailedException.class, ConversionFailedException.class })
    public ResponseEntity<Object> handleConflict(ConversionException ex, WebRequest request) {
        return handleExceptionInternal(ex, format("Conversion failed: [{0}]", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { PropertyReferenceException.class })
    protected ResponseEntity<Object> handleInvalidParam(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, format("Invalid property specified: [{0}]", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, format("Unexpected error: [{0}]", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}