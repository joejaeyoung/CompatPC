package com.example.intelligence.service;

import com.example.intelligence.domain.hardware.*;
import com.example.intelligence.repository.ComputerRepository;
import com.example.intelligence.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComputerNameService {
    private final ComputerRepository computerRepository;

    public ServiceResponseCase getCaseByName(String name) {
        Cases result = computerRepository.getCaseByName(name);
        ServiceResponseCase serviceResponseCase = new ServiceResponseCase();
        serviceResponseCase.setName(result.getName());
        serviceResponseCase.setImg(result.getImg());
        return serviceResponseCase;
    }

    public ServiceResponseCooler getCoolerByName(String name) {
        Cooler result = computerRepository.getCoolerByName(name);
        ServiceResponseCooler serviceResponseCooler = new ServiceResponseCooler();
        serviceResponseCooler.setName(result.getName());
        serviceResponseCooler.setImg(result.getImg());
        return serviceResponseCooler;
    }

    public ServiceResponseCpu getCPUByName(String name) {
        CPU result = computerRepository.getCPUByName(name);
        ServiceResponseCpu serviceResponseCpu = new ServiceResponseCpu();
        serviceResponseCpu.setName(result.getName());
        serviceResponseCpu.setImg(result.getImg());
        return serviceResponseCpu;
    }

    public ServiceResponseGpu getGPUByName(String name) {
        GPU result = computerRepository.getGPUByName(name);
        ServiceResponseGpu serviceResponseGpu = new ServiceResponseGpu();
        serviceResponseGpu.setName(result.getName());
        serviceResponseGpu.setImg(result.getImg());
        return serviceResponseGpu;
    }

    public ServiceResponseHdd getHDDByName(String name) {
        HDD result = computerRepository.getHDDByName(name);
        ServiceResponseHdd serviceResponseHdd = new ServiceResponseHdd();
        serviceResponseHdd.setName(result.getName());
        serviceResponseHdd.setImg(result.getImg());
        return serviceResponseHdd;
    }

    public ServiceResponseMainboard getMainboardByName(String name) {
        Mainboard result = computerRepository.getMainboardByName(name);
        ServiceResponseMainboard serviceResponseMainboard = new ServiceResponseMainboard();
        serviceResponseMainboard.setName(result.getName());
        serviceResponseMainboard.setImg(result.getImg());
        return serviceResponseMainboard;
    }

    public ServiceResponsePowerSupply getPowerSupplyByName(String name) {
        PSU result = computerRepository.getPowerSupplyByName(name);
        ServiceResponsePowerSupply serviceResponsePowerSupply = new ServiceResponsePowerSupply();
        serviceResponsePowerSupply.setName(result.getName());
        serviceResponsePowerSupply.setImg(result.getImg());
        return serviceResponsePowerSupply;
    }

    public ServiceResponseRam getRAMByName(String name) {
        RAM result = computerRepository.getRAMByName(name);
        ServiceResponseRam serviceResponseRam = new ServiceResponseRam();
        serviceResponseRam.setName(result.getName());
        serviceResponseRam.setImg(result.getImg());
        return serviceResponseRam;
    }

    public ServiceResponseSsd getSSDByName(String name) {
        SSD result = computerRepository.getSSDByName(name);
        ServiceResponseSsd serviceResponseSsd = new ServiceResponseSsd();
        serviceResponseSsd.setName(result.getName());
        serviceResponseSsd.setImg(result.getImg());
        return serviceResponseSsd;
    }
}
