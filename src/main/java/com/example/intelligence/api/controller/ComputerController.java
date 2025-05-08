package com.example.intelligence.api.controller;

import com.example.intelligence.api.ApiResponse;
import com.example.intelligence.api.controller.dto.UserRequest;
import com.example.intelligence.api.controller.dto.UserResponse;
import com.example.intelligence.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/computer/valid")
public class ComputerController {
    private final ValidateService validateService;

    @PostMapping("/validate")
    public ApiResponse<UserResponse> validate(@RequestBody UserRequest userRequest) {
        log.info("User Request info :: {}", userRequest);
        return ApiResponse.ok(validateService.checkValidation(userRequest.userRequesttoServiceUserRequest()));
    }
}
