package com.example.intelligence.service;

import com.example.intelligence.domain.hardware.*;
import com.example.intelligence.exception.respository.FindNullException;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.service.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.dto.validation.ServiceValidationResponse;
import com.example.intelligence.service.hardware.*;
import com.example.intelligence.service.validation.CoolerValidation;
import com.example.intelligence.service.validation.CpuValidation;
import com.example.intelligence.service.validation.MainboardValidation;
import com.example.intelligence.service.validation.RamValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComputerService {
    private final CaseService caseService;
    private final CoolerService coolerService;
    private final CpuService cpuService;
    private final GpuService gpuService;
    private final HddService hddService;
    private final MainboardService mainboardService;
    private final PsuService psuService;
    private final RamService ramService;
    private final SsdService ssdService;

    public void updateAll() throws IOException {
        caseService.updateAll();
        coolerService.updateAll();
        cpuService.updateAll();
        gpuService.updateAll();
        hddService.updateAll();
        mainboardService.updateAll();
        psuService.updateAll();
        ramService.updateAll();
        ssdService.updateAll();
    }


    public List<ServiceValidationResponse> checkValidation(ServiceUserRequest request) {
        List<ServiceValidationResponse> result = new ArrayList<>();
        CpuValidation cpuValidation = new CpuValidation();
        CoolerValidation coolerValidation = new CoolerValidation();
        MainboardValidation mainboardValidation = new MainboardValidation();
        RamValidation ramValidation = new RamValidation();
        Cases cases = null;
        Cooler cooler = null;
        CPU cpu = null;
        GPU gpu;
        HDD hdd;
        Mainboard mainboard = null;
        PSU psu;
        RAM ram = null;
        SSD ssd;

        try {
            cases = caseService.getById(request.getCaseId());
            cooler = coolerService.getById(request.getCoolerId());
            cpu = cpuService.getById(request.getCpuId());
            gpu = gpuService.getById(request.getGpuId());
            hdd = hddService.getById(request.getHddId());
            mainboard = mainboardService.getById(request.getMainboardId());
            psu = psuService.getById(request.getPsuId());
            ram = ramService.getById(request.getRamId());
            ssd = ssdService.getById(request.getSsdId());
        } catch (HWException e) {
            result.add(new ServiceValidationResponse("부품 중 DB에서 부품 정보를 받아올 수 없는 부품이 있습니다.", "",1));
            return result;
        }

        cpuValidation.checkWithCooler(cpu, cooler);
        cpuValidation.checkWithMainboard(cpu, mainboard);
        for(ServiceValidationResponse msg : cpuValidation.errorMsg) {
            result.add(msg);
        }

        coolerValidation.checkWithCase(cooler, cases);
        for (ServiceValidationResponse msg : coolerValidation.errorMsg) {
            result.add(msg);
        }

        mainboardValidation.checkWithRam(mainboard, ram, request);
        mainboardValidation.checkWithSSD(mainboard, request);
        mainboardValidation.checkWithHDD(mainboard, request);
        mainboardValidation.checkWithCase(mainboard, cases);
        for (ServiceValidationResponse msg : mainboardValidation.errorMsg) {
            result.add(msg);
        }

        ramValidation.checkWithRam(request);
        for (ServiceValidationResponse msg : ramValidation.errorMsg) {
            result.add(msg);
        }

        return result;
    }


}
