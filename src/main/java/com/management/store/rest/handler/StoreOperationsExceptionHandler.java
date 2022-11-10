package com.management.store.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StoreOperationsExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleUserDataErrors(RuntimeException ex) {
        String errorMessage = """
                Incorrect request data.
                Details: %s.
                Retry the request with valid input.
                """;
        return new ResponseEntity<>(String.format(errorMessage, ex.getMessage()), HttpStatus.PRECONDITION_FAILED);
    }
}
