package com.management.store.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionInfo {
    private String message;
    private String details;
}
