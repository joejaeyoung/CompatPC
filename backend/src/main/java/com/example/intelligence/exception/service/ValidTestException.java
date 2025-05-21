package com.example.intelligence.exception.service;

import com.example.intelligence.exception.respository.FindNullErrorCode;
import org.springframework.http.HttpStatus;

public class ValidTestException extends RuntimeException {
    private HttpStatus status;
    public ValidTestException(FindNullErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }
}
