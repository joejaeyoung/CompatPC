package com.example.intelligence.service;

import com.example.intelligence.domain.hardware.*;
import com.example.intelligence.repository.ComputerRepository;
import com.example.intelligence.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
//각 부품 정보 전체 보내기
public class ComputerService {
    private final ComputerRepository computerRepository;

    public List<ServiceResponseCase> getCases() {
        List<Cases> cases = computerRepository.getCases();
        List<ServiceResponseCase> result = new ArrayList<>();

        for(Cases c : cases) {
            ServiceResponseCase serviceResponseCase = new ServiceResponseCase();
            serviceResponseCase.setName(c.getName());
            serviceResponseCase.setImg(c.getImg());
            result.add(serviceResponseCase);
        }
        return result;
    }

    public List<ServiceResponseCooler> getCoolers() {
        List<Cooler> coolers = computerRepository.getCoolers();
        List<ServiceResponseCooler> result = new ArrayList<>();

        for(Cooler c : coolers) {
            ServiceResponseCooler serviceResponseCooler = new ServiceResponseCooler();
            serviceResponseCooler.setName(c.getName());
            serviceResponseCooler.setImg(c.getImg());
            result.add(serviceResponseCooler);
        }
        return result;
    }

    public List<ServiceResponseCpu> getCPUs() {
        List<CPU> cpus = computerRepository.getCPUs();
        List<ServiceResponseCpu> result = new ArrayList<>();

        for(CPU c : cpus) {
            ServiceResponseCpu cpu = new ServiceResponseCpu();
            cpu.setName(c.getName());
            cpu.setImg(c.getImg());
            result.add(cpu);
        }
        return result;
    }

    public List<ServiceResponseGpu> getGPUs() {
        List<GPU> gpus = computerRepository.getGPUs();
        List<ServiceResponseGpu> result = new ArrayList<>();

        for(GPU c : gpus) {
            ServiceResponseGpu gpu = new ServiceResponseGpu();
            gpu.setName(c.getName());
            gpu.setImg(c.getImg());
            result.add(gpu);
        }
        return result;
    }

    public List<ServiceResponseHdd> getHDDs() {
        List<HDD> hdds = computerRepository.getHDDs();
        List<ServiceResponseHdd> result = new ArrayList<>();

        for(HDD c : hdds) {
            ServiceResponseHdd hdd = new ServiceResponseHdd();
            hdd.setName(c.getName());
            hdd.setImg(c.getImg());
            result.add(hdd);
        }
        return result;
    }

    public List<ServiceResponseMainboard> getMainboards() {
        List<Mainboard> mainboards = computerRepository.getMainboards();
        List<ServiceResponseMainboard> result = new ArrayList<>();

        for(Mainboard c : mainboards) {
            ServiceResponseMainboard mainboard = new ServiceResponseMainboard();
            mainboard.setName(c.getName());
            mainboard.setImg(c.getImg());
            result.add(mainboard);
        }
        return result;
    }

    public List<ServiceResponsePowerSupply> getPowerSupply() {
        List<PSU> powersupplies = computerRepository.getPowerSupply();
        List<ServiceResponsePowerSupply> result = new ArrayList<>();

        for(PSU c : powersupplies) {
            ServiceResponsePowerSupply powersupply = new ServiceResponsePowerSupply();
            powersupply.setName(c.getName());
            powersupply.setImg(c.getImg());
            result.add(powersupply);
        }
        return result;
    }

    public List<ServiceResponseRam> getRAMs() {
        List<RAM> rams = computerRepository.getRAMs();
        List<ServiceResponseRam> result = new ArrayList<>();

        for(RAM c : rams) {
            ServiceResponseRam ram = new ServiceResponseRam();
            ram.setName(c.getName());
            ram.setImg(c.getImg());
            result.add(ram);
        }
        return result;
    }

    public List<ServiceResponseSsd> getSSDs() {
        List<SSD> ssds = computerRepository.getSSDs();
        List<ServiceResponseSsd> result = new ArrayList<>();

        for(SSD c : ssds) {
            ServiceResponseSsd ssd = new ServiceResponseSsd();
            ssd.setName(c.getName());
            ssd.setImg(c.getImg());
            result.add(ssd);
        }
        return result;
    }

}
