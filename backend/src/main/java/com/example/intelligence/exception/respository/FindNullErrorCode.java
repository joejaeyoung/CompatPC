package com.example.intelligence.exception.respository;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FindNullErrorCode {
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

    FindNullErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}