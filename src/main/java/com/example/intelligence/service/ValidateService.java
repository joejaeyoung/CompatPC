package com.example.intelligence.service;

import com.example.intelligence.api.controller.dto.UserResponse;
import com.example.intelligence.service.dto.UserRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateService {
    public UserResponse checkValidation(UserRequestService request) {
        UserResponse userResponse = new UserResponse();
        /*
        @todo 호환성 체크 로직 메소드 호출 및 데이터 삽입
         */



        return userResponse;
    }
}
