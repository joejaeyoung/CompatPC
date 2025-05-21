package com.example.intelligence.api.controller;

import com.example.intelligence.api.ApiResponse;
import com.example.intelligence.service.hardware.*;
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
public class HWInfoController {
    private final CaseService caseService;
    private final CoolerService coolerService;
    private final CpuService cpuService;
    private final GpuService gpuService;
    private final HddService hddService;
    private final MainboardService mainboardService;
    private final PsuService psuService;
    private final RamService ramService;
    private final SsdService ssdService;

    @GetMapping("/case")
    public ApiResponse getCase(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(caseService.getByname(name));
        return ApiResponse.ok(caseService.getAll());
    }

    @GetMapping("/cooler")
    public ApiResponse getCooler(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(coolerService.getByName(name));
        return ApiResponse.ok(coolerService.getAll());
    }

    @GetMapping("/cpu")
    public ApiResponse getCPU(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(cpuService.getByName(name));
        return ApiResponse.ok(cpuService.getAll());
    }

    @GetMapping("/gpu")
    public ApiResponse getGPU(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(gpuService.getByName(name));
        return ApiResponse.ok(gpuService.getAll());
    }

    @GetMapping("/hdd")
    public ApiResponse getHDD(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(hddService.getByName(name));
        return ApiResponse.ok(hddService.getAll());
    }

    @GetMapping("/mainboard")
    public ApiResponse getMainboard(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(mainboardService.getByName(name));
        return ApiResponse.ok(mainboardService.getAll());
    }

    @GetMapping("/powersupply")
    public ApiResponse getPowerSupply(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(psuService.getByName(name));
        return ApiResponse.ok(psuService.getAll());
    }

    @GetMapping("/ram")
    public ApiResponse getRAM(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(ramService.getByName(name));
        return ApiResponse.ok(ramService.getAll());
    }

    @GetMapping("/ssd")
    public ApiResponse getSSD(@RequestParam(required = false) String name) {
        if (name != null)
            return ApiResponse.ok(ssdService.getByName(name));
        return ApiResponse.ok(ssdService.getAll());
    }
}
