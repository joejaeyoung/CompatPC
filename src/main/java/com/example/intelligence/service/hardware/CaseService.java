package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.CaseRepository;
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
public class CaseService {

    @Autowired
    private ObjectMapper objectMapper;
    private final CaseRepository caseRepository;

    public ServiceResponseCpu getByname(String name) {
        Cases result = caseRepository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_CASE));
        ServiceResponseCpu serviceResponseCpu = new ServiceResponseCpu();
        serviceResponseCpu.setName(result.getName());
        serviceResponseCpu.setImg(result.getImageUrl());
        return serviceResponseCpu;
    }

    public Cases getById(Long id) {
        Cases result = caseRepository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_CASE));
        return result;
    }

    public List<ServiceResponseCpu> getAll() {
        List<Cases> cpus = caseRepository.getAll();
        List<ServiceResponseCpu> result = new ArrayList<>();

        for(Cases c : cpus) {
            ServiceResponseCpu cpu = new ServiceResponseCpu();
            cpu.setId(c.getId());
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("Case.json").getInputStream();

        List<Cases> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cases.class));
        log.info("cpus: {}", cpus);
        caseRepository.saveAll(cpus);
    }
}
