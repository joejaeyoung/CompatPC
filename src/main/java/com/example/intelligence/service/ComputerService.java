package com.example.intelligence.service;

import com.example.intelligence.domain.hardware.*;
import com.example.intelligence.exception.user.HWErrorCode;
import com.example.intelligence.exception.user.HWException;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;
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

    public void preprocessUserRequest(ServiceUserRequest request) throws HWException{
        //ssd 전처리
        List<Long> ssdIds = new ArrayList<>();
        ssdIds = request.getSsdId();
        SSD ssd;


        //cooler 전처리
        Cooler cooler;
        String grade;
        cooler = coolerService.getById(request.getCoolerId());
        grade = cooler.getCoolerGrade();

        if (grade == "번들쿨러") {
            request.setCoolerTdp(80);
        }
        else if (grade == "1열수랭" || grade == "싱글타워") {
            request.setCoolerTdp(160);
        }
        else if (grade == "2열수랭" || grade == "듀얼타워") {
            request.setCoolerTdp(220);
        }
        else if (grade == "3열수랭") {
            request.setCoolerTdp(999);
        }
        else {
            throw new HWException(HWErrorCode.NOT_FOUND_COOLER);
        }
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
        GPU gpu = null;
        Mainboard mainboard = null;
        PSU psu = null ;
        RAM ram = null;

        try {
            preprocessUserRequest(request);
            cases = caseService.getById(request.getCaseId());

            if (request.getCoolerId() != null)
                cooler = coolerService.getById(request.getCoolerId());
            cpu = cpuService.getById(request.getCpuId());

            if (request.getGpuId() != null)
                gpu = gpuService.getById(request.getGpuId());
            mainboard = mainboardService.getById(request.getMainboardId());
            psu = psuService.getById(request.getPsuId());
            ram = ramService.getById(request.getRamId());
        } catch (HWException e) {
            result.add(new ServiceValidationResponse(e.getMessage() , "",1));
            return result;
        }

        //210, 211, 220
        if (cooler != null) {
            coolerValidation.checkWithMainboard(request, cooler, mainboard);
            coolerValidation.checkWithCPU(request, cooler, cpu);
            coolerValidation.checkWithCPUCooler(request, cooler, cpu);
            for (ServiceValidationResponse msg : coolerValidation.errorMsg) {
                result.add(msg);
            }
        }

        //310 311
        cpuValidation.checkWithMainboard(cpu, mainboard);
        for (ServiceValidationResponse msg : cpuValidation.errorMsg) {
            result.add(msg);
        }

        //430 431 432 433 434
        mainboardValidation.checkWithRam(request, mainboard, ram);
        for (ServiceValidationResponse msg : mainboardValidation.errorMsg) {
            result.add(msg);
        }

        //440
        ramValidation.checkWithRam(request);
        for (ServiceValidationResponse msg : ramValidation.errorMsg) {
            result.add(msg);
        }

        return result;
    }
}
