package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.Mainboard;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.MainboardRepository;
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
public class MainboardService {

    @Autowired
    private ObjectMapper objectMapper;
    private final MainboardRepository repository;

    public ServiceResponseSsd getByName(String name) {
        Mainboard result = repository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_MAINBOARD));
        ServiceResponseSsd serviceResponseSsd = new ServiceResponseSsd();
        serviceResponseSsd.setName(result.getName());
        serviceResponseSsd.setImg(result.getImageUrl());
        return serviceResponseSsd;
    }

    public Mainboard getById(Long id) {
        Mainboard result = repository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_MAINBOARD));
        return result;
    }

    public List<ServiceResponseSsd> getAll() {
        List<Mainboard> cpus = repository.getAll();
        List<ServiceResponseSsd> result = new ArrayList<>();

        for(Mainboard c : cpus) {
            ServiceResponseSsd cpu = new ServiceResponseSsd();
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("Mainboard.json").getInputStream();

        List<Mainboard> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Mainboard.class));
        log.info("cpus: {}", cpus);
        repository.saveAll(cpus);
    }
}
