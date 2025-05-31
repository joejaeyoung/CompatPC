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
    private final List<ServiceValidationResponse> result = new ArrayList<>();

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
        SSD ssd;
        for (Long ssdId : request.getSsdId()) {
            try {
                ssd = ssdService.getById(ssdId);
                if (ssd.getSocket().contains("M.2")) {
                    request.setM2ssdCount(request.getM2ssdCount() + 1);
                }
                else {
                    request.setSatassdCount(request.getSatassdCount() + 1);
                }
            }
            catch (HWException e) {
                result.add(new ServiceValidationResponse(ssdId + " : 에 해당하는 ssd를 DB에서 찾아올 수 없습니다.", "", 1));
            }
        }

        if (request.getSsdId() == null) {
            request.setSatassdCount(0);
            request.setM2ssdCount(0);
        }


        //cooler 전처리
        Cooler cooler;
        String grade;

        if (request.getCoolerId() == null) {
            request.setCoolerTdp(80);
        }
        else {
            cooler = coolerService.getById(request.getCoolerId());
            grade = cooler.getCoolerGrade();

            if (grade == "번들쿨러") {
                request.setCoolerTdp(80);
            } else if (grade == "1열수랭" || grade == "싱글타워") {
                request.setCoolerTdp(160);
            } else if (grade == "2열수랭" || grade == "듀얼타워") {
                request.setCoolerTdp(220);
            } else if (grade == "3열수랭" || grade == "무제한") {
                request.setCoolerTdp(999);
            } else {
                throw new HWException(HWErrorCode.NOT_FOUND_COOLER);
            }
        }
    }

    public List<ServiceValidationResponse> checkValidation(ServiceUserRequest request) {
        result.clear();
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

        //200
        if (cooler == null && cpu.isHasCooler() ==false) {
            result.add(new ServiceValidationResponse("CPU에 쿨러가 포함되어 있지 않은 제품입니다. ", "쿨러를 선택해주세요.", 1));
        }
        if (!(cooler != null && cpu.isHasCooler() == false)) {
            //210
            coolerValidation.checkWithMainboard(request, cooler, mainboard);
            //211
            coolerValidation.checkWithCPU(request, cooler, cpu);
        }
        //220
        coolerValidation.checkWithCPUCooler(request, cooler, cpu);
        for (ServiceValidationResponse msg : coolerValidation.errorMsg) {
            result.add(msg);
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

        //630

        //730

        //820
        coolerValidation.errorMsg.clear();
        coolerValidation.checkWithCase(request, cooler, cases);
        for (ServiceValidationResponse msg : coolerValidation.errorMsg) {
            result.add(msg);
        }

        return result;
    }
}
