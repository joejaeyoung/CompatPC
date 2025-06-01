package com.example.intelligence.service.dto.validation;

import lombok.Getter;

@Getter
public class ServiceValidationResponse {
    private String errMsg;
    private String solveMsg;
    private int errorLevel;

    public ServiceValidationResponse(String errMsg, String solveMsg, int errorLevel) {
        this.errMsg = errMsg;
        this.solveMsg = solveMsg;
        this.errorLevel = errorLevel;
    }
}
