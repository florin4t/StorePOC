package com.management.store.rest.handler;

import com.management.store.rest.exception.ExceptionInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StoreOperationsExceptionHandler {
    private static final String MSG_USER_ERROR = "Incorrect request data. Retry the request with valid input.";

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ExceptionInfo> handleUserDataErrors(RuntimeException ex) {
        return new ResponseEntity<>(
                new ExceptionInfo(MSG_USER_ERROR, ex.getMessage()),
                HttpStatus.PRECONDITION_FAILED);
    }
}
