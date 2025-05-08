package com.example.intelligence.api.controller;

import com.example.intelligence.api.ApiResponse;
import com.example.intelligence.service.ComputerNameService;
import com.example.intelligence.service.ComputerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/computer")
public class ComputerInfoController {
    private final ComputerService computerService;
    private final ComputerNameService computerNameService;

    @GetMapping("/case")
    public ApiResponse getCase(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getCaseByName(name));
        return ApiResponse.ok(computerService.getCases());
    }

    @GetMapping("/cooler")
    public ApiResponse getCooler(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getCoolerByName(name));
        return ApiResponse.ok(computerService.getCoolers());
    }

    @GetMapping("/cpu")
    public ApiResponse getCPU(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getCPUByName(name));
        return ApiResponse.ok(computerService.getCPUs());
    }

    @GetMapping("/gpu")
    public ApiResponse getGPU(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getGPUByName(name));
        return ApiResponse.ok(computerService.getGPUs());
    }

    @GetMapping("/hdd")
    public ApiResponse getHDD(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getHDDByName(name));
        return ApiResponse.ok(computerService.getHDDs());
    }

    @GetMapping("/mainboard")
    public ApiResponse getMainboard(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getMainboardByName(name));
        return ApiResponse.ok(computerService.getMainboards());
    }

    @GetMapping("/powersupply")
    public ApiResponse getPowerSupply(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getPowerSupplyByName(name));
        return ApiResponse.ok(computerService.getPowerSupply());
    }

    @GetMapping("/ram")
    public ApiResponse getRAM(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getRAMByName(name));
        return ApiResponse.ok(computerService.getRAMs());
    }

    @GetMapping("/ssd")
    public ApiResponse getSSD(@RequestParam(required = false) String name) {
        if (name == null)
            return ApiResponse.ok(computerNameService.getSSDByName(name));
        return ApiResponse.ok(computerService.getSSDs());
    }
}
