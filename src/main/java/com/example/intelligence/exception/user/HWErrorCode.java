package com.example.intelligence.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum HWErrorCode {

    NOT_FOUND("해당 부품을 조회할 수 없습니다.", HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus httpStatus;

    HWErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
