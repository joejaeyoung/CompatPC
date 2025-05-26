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
    public ApiResponse<List<ServiceValidationResponse>> validate(@RequestBody ValidateUserRequest re) {
        log.info("User Request info :: {}", re);
        return ApiResponse.ok(computerService.checkValidation(re.UserRuserRequesttoServiceUserRequest(re.getCpuId(), re.getCoolerId(), re.getCoolerTdp(), re.getMainboardId(), re.getRamId(), re.getRamQunatatiy(), re.getRamCapacity(), re.getGpuId(), re.getSsdId(), re.getM2ssdCount(), re.getSatassdCount(), re.getHddCount(), re.getCaseId(), re.getPsuId())));
    }
}
