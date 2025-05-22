package com.example.intelligence.api.controller;

import com.example.intelligence.api.ApiResponse;
import com.example.intelligence.api.controller.dto.ValidateUserRequest;
import com.example.intelligence.service.ComputerService;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/computer")
public class ComputerController {
    private final ComputerService computerService;

    @PostMapping("/validate")
    public ApiResponse<List<ServiceValidationResponse>> validate(@RequestBody ValidateUserRequest validateUserRequest) {
        log.info("User Request info :: {}", validateUserRequest);
        return ApiResponse.ok(computerService.checkValidation(validateUserRequest.UserRuserRequesttoServiceUserRequest(validateUserRequest.getCpuId(), validateUserRequest.getCoolerId(), validateUserRequest.getMainboardId(), validateUserRequest.getRamId(), validateUserRequest.getRamQunatatiy(), validateUserRequest.getRamCapacity(), validateUserRequest.getGpuId(), validateUserRequest.getSsdId(), validateUserRequest.getM2ssdCount(), validateUserRequest.getSatassdCount(), validateUserRequest.getHddId(), validateUserRequest.getHddCount(), validateUserRequest.getCaseId(), validateUserRequest.getPsuId())));
    }
}
