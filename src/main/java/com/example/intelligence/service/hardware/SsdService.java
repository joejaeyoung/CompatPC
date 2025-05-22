package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.SSD;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.SsdRepository;
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
public class SsdService {

    @Autowired
    private ObjectMapper objectMapper;
    private final SsdRepository ssdRepository;

    public ServiceResponseSsd getByName(String name) {
        SSD result = ssdRepository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_SSD));
        ServiceResponseSsd serviceResponseSsd = new ServiceResponseSsd();
        serviceResponseSsd.setName(result.getName());
        serviceResponseSsd.setImg(result.getImageUrl());
        return serviceResponseSsd;
    }

    public SSD getById(Long id) {
        SSD result = ssdRepository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_SSD));
        return result;
    }

    public List<ServiceResponseSsd> getAll() {
        List<SSD> cpus = ssdRepository.getAll();
        List<ServiceResponseSsd> result = new ArrayList<>();

        for(SSD c : cpus) {
            ServiceResponseSsd cpu = new ServiceResponseSsd();
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("SSD.json").getInputStream();

        List<SSD> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, SSD.class));
        log.info("cpus: {}", cpus);
        ssdRepository.saveAll(cpus);
    }
}
