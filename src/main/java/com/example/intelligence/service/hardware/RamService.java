package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.RAM;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.RamRepository;
import com.example.intelligence.service.hardware.dto.hardware.ServiceResponseSsd;
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
public class RamService {

    @Autowired
    private ObjectMapper objectMapper;
    private final RamRepository repository;

    public ServiceResponseSsd getByName(String name) {
        RAM result = repository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_RAM));
        ServiceResponseSsd serviceResponseSsd = new ServiceResponseSsd();
        serviceResponseSsd.setName(result.getName());
        serviceResponseSsd.setImg(result.getImageUrl());
        return serviceResponseSsd;
    }

    public RAM getById(Long id) {
        RAM result = repository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_RAM));
        return result;
    }

    public List<ServiceResponseSsd> getAll() {
        List<RAM> cpus = repository.getAll();
        List<ServiceResponseSsd> result = new ArrayList<>();

        for(RAM c : cpus) {
            ServiceResponseSsd cpu = new ServiceResponseSsd();
            cpu.setId(c.getId());
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("RAM.json").getInputStream();

        List<RAM> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, RAM.class));
        log.info("cpus: {}", cpus);
        repository.saveAll(cpus);
    }
}
