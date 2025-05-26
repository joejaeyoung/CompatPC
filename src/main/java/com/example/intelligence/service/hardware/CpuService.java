package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.CPU;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.CpuRepository;
import com.example.intelligence.service.hardware.dto.hardware.ServiceResponseCpu;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CpuService {

    @Autowired
    private ObjectMapper objectMapper;
    private final CpuRepository cpuRepository;

    public ServiceResponseCpu getByName(String name) {
        CPU result = cpuRepository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_CPU));
        ServiceResponseCpu serviceResponseCpu = new ServiceResponseCpu();
        serviceResponseCpu.setName(result.getName());
        serviceResponseCpu.setImg(result.getImageUrl());
        return serviceResponseCpu;
    }

    public CPU getById(Long id) {
        CPU result = cpuRepository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_CPU));
        return result;
    }

    public List<ServiceResponseCpu> getAll() {
        List<CPU> cpus = cpuRepository.getAll();
        List<ServiceResponseCpu> result = new ArrayList<>();

        for(CPU c : cpus) {
            ServiceResponseCpu cpu = new ServiceResponseCpu();
            cpu.setId(c.getId());
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("CPU.json").getInputStream();

        List<CPU> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CPU.class));
        log.info("cpus: {}", cpus);
        cpuRepository.saveAll(cpus);
    }
}
