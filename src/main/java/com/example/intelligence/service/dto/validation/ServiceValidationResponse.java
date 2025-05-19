package com.example.intelligence.service.dto.validation;

import lombok.Getter;

@Getter
public class ServiceValidationResponse {
    private String msg;
    private int errorLevel;

    public ServiceValidationResponse(String msg, int errorLevel) {
        this.msg = msg;
        this.errorLevel = errorLevel;
    }
}
