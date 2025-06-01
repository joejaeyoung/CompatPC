package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.CPU;
import com.example.intelligence.domain.hardware.HDD;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.HddRepository;
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
public class HddService {

    @Autowired
    private ObjectMapper objectMapper;
    private final HddRepository repository;

    public ServiceResponseSsd getByName(String name) {
        HDD result = repository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND));
        ServiceResponseSsd serviceResponseSsd = new ServiceResponseSsd();
        serviceResponseSsd.setName(result.getName());
        serviceResponseSsd.setImg(result.getImageUrl());
        return serviceResponseSsd;
    }

    public HDD getById(Long id) {
        HDD result = repository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND));
        return result;
    }

    public List<ServiceResponseSsd> getAll() {
        List<HDD> cpus = repository.getAll();
        List<ServiceResponseSsd> result = new ArrayList<>();

        for(HDD c : cpus) {
            ServiceResponseSsd cpu = new ServiceResponseSsd();
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("HDD.json").getInputStream();

        List<HDD> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, HDD.class));
        log.info("cpus: {}", cpus);
        repository.saveAll(cpus);
    }
}
