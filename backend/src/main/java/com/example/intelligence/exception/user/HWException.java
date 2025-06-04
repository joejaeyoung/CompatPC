package com.example.intelligence.exception.user;

import org.springframework.http.HttpStatus;

public class HWException extends RuntimeException {

  private HttpStatus httpStatus;

  public HWException(HWErrorCode errorCode) {

    super(errorCode.getMessage());
    this.httpStatus = errorCode.getHttpStatus();
  }
}
