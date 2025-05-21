package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.CPU;
import com.example.intelligence.domain.hardware.PSU;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.PsuRepository;
import com.example.intelligence.service.dto.hardware.ServiceResponseSsd;
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
public class PsuService {

    @Autowired
    private ObjectMapper objectMapper;
    private final PsuRepository repository;

    public ServiceResponseSsd getByName(String name) {
        PSU result = repository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND));
        ServiceResponseSsd serviceResponseSsd = new ServiceResponseSsd();
        serviceResponseSsd.setName(result.getName());
        serviceResponseSsd.setImg(result.getImageUrl());
        return serviceResponseSsd;
    }

    public PSU getById(Long id) {
        PSU result = repository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND));
        return result;
    }

    public List<ServiceResponseSsd> getAll() {
        List<PSU> cpus = repository.getAll();
        List<ServiceResponseSsd> result = new ArrayList<>();

        for(PSU c : cpus) {
            ServiceResponseSsd cpu = new ServiceResponseSsd();
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("PSU.json").getInputStream();

        List<PSU> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, PSU.class));
        log.info("cpus: {}", cpus);
        repository.saveAll(cpus);
    }
}
