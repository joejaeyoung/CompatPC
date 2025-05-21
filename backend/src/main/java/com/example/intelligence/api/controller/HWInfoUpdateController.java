package com.example.intelligence.api.controller;

import com.example.intelligence.api.ApiResponse;
import com.example.intelligence.service.ComputerService;
import com.example.intelligence.service.hardware.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/update")
public class HWInfoUpdateController {

    private final ComputerService computerService;

    @GetMapping("/all")
    public ApiResponse updateAll() throws IOException {
        computerService.updateAll();
        return ApiResponse.ok("update finish");
    }
}
