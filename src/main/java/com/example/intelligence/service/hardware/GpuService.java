package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.GPU;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.GpuRepository;
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
public class GpuService {

    @Autowired
    private ObjectMapper objectMapper;
    private final GpuRepository repository;

    public ServiceResponseSsd getByName(String name) {
        GPU result = repository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_GPU));
        ServiceResponseSsd serviceResponseSsd = new ServiceResponseSsd();
        serviceResponseSsd.setName(result.getName());
        serviceResponseSsd.setImg(result.getImageUrl());
        return serviceResponseSsd;
    }

    public GPU getById(Long id) {
        GPU result = repository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_GPU));
        return result;
    }

    public List<ServiceResponseSsd> getAll() {
        List<GPU> cpus = repository.getAll();
        List<ServiceResponseSsd> result = new ArrayList<>();

        for(GPU c : cpus) {
            ServiceResponseSsd cpu = new ServiceResponseSsd();
            cpu.setId(c.getId());
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("GPU.json").getInputStream();

        List<GPU> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, GPU.class));
        log.info("cpus: {}", cpus);
        repository.saveAll(cpus);
    }
}
