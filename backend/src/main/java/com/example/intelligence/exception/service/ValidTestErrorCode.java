package com.example.intelligence.exception.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ValidTestErrorCode {
    /*
    검증 알고리즘 돌릴 때 알고리즘 별로 exception class 만들고, enum 형태로 받아서 정해진 문구 바로 출력할 수 있게 하면 됩니다.
    */
    NOT_FOUND_CASE("Case를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_COOLER("Cooler를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_CPU("CPU를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_GPU("GPU를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_HDD("HDD를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_MAINBOARD("Main Board를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_POWERSUPPLY("Power Supply를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_RAM("RAM를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_SSD("SSD를 조회할 수 없습니다.", HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    ValidTestErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}