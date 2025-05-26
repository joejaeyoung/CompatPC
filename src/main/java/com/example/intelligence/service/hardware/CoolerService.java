package com.example.intelligence.service.hardware;

import com.example.intelligence.domain.hardware.Cooler;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.repository.hardware.CoolerRepository;
import com.example.intelligence.service.hardware.dto.hardware.ServiceResponseSsd;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoolerService {

    @Autowired
    private ObjectMapper objectMapper;
    private final CoolerRepository repository;

    public ServiceResponseSsd getByName(String name) {
        Cooler result = repository.findByName(name)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_COOLER));
        ServiceResponseSsd serviceResponseSsd = new ServiceResponseSsd();
        serviceResponseSsd.setName(result.getName());
        serviceResponseSsd.setImg(result.getImageUrl());
        return serviceResponseSsd;
    }

    public Cooler getById(Long id) {
        Cooler result = repository.findById(id)
                .orElseThrow(() -> new HWException(HWErrorCode.NOT_FOUND_COOLER));
        return result;
    }

    public List<ServiceResponseSsd> getAll() {
        List<Cooler> cpus = repository.getAll();
        List<ServiceResponseSsd> result = new ArrayList<>();

        for(Cooler c : cpus) {
            ServiceResponseSsd cpu = new ServiceResponseSsd();
            cpu.setId(c.getId());
            cpu.setName(c.getName());
            cpu.setImg(c.getImageUrl());
            result.add(cpu);
        }
        return result;
    }

    public void updateAll() throws IOException {
        InputStream inputStream = new ClassPathResource("Cooler.json").getInputStream();

        List<Cooler> cpus = objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cooler.class));
        log.info("cpus: {}", cpus);
        repository.saveAll(cpus);
    }

    private static File getFile(Resource resource) throws IOException {
        return resource.getFile();
    }
}
