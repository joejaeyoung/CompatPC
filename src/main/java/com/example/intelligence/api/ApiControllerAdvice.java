package com.example.intelligence.api;

import com.example.intelligence.exception.respository.FindNullException;
import com.example.intelligence.exception.user.HWException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, FindNullException.class})
    public ApiResponse<String> illegalArgumentException(RuntimeException e) {
        return ApiResponse.badRequest(e.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ApiResponse<String> numberFormatException(NumberFormatException e) {
        return ApiResponse.badRequest("올바르지 않은 타입입니다. 다시 확인해 주세요");
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HWException.class)
    public ApiResponse<String> hwException(HWException e) {
        return ApiResponse.badRequest(e.getMessage());
    }

}