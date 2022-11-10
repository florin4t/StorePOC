package com.management.store.rest.handler;

import com.management.store.rest.exception.ExceptionInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class StoreOperationsExceptionHandler {
    private static final String MSG_USER_ERROR = "Incorrect request data. Retry the request with valid input.";
    private static final String MSG_USER_VALIDATION_ERROR = "The data in the request does not meet all the validation criteria.";

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ExceptionInfo> handleUserDataErrors(RuntimeException ex) {
        return new ResponseEntity<>(
                new ExceptionInfo(MSG_USER_ERROR, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionInfo> handleValidationErrors(MethodArgumentNotValidException ex) {
        HashMap<String, String> errorsMap = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .stream()
                .forEach(e -> errorsMap.put(((FieldError) e).getField(), e.getDefaultMessage()));
        return new ResponseEntity<>(
                new ExceptionInfo(MSG_USER_VALIDATION_ERROR, errorsMap.toString()),
                HttpStatus.PRECONDITION_FAILED);
    }
}
