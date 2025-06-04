package com.example.intelligence.exception.respository;

import org.springframework.http.HttpStatus;

public class FindNullException extends RuntimeException {
    private HttpStatus status;
    public FindNullException(FindNullErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }

    public FindNullException(String message) {
        super(message);
    }
}
