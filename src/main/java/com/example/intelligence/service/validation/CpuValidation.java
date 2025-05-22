package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.CPU;
import com.example.intelligence.domain.hardware.Cooler;
import com.example.intelligence.domain.hardware.Mainboard;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class CpuValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    public void checkWithCooler(ServiceUserRequest request, CPU cpu, Cooler cooler) {
        //210
        if ((cpu.getSocket() & cooler.getSupportedSockets()) != 1) {
            errorMsg.add(new ServiceValidationResponse("쿨러가 CPU 소켓을 지원하지 않음", "", 1));
        }

        //211
    }

    public void checkWithMainboard(CPU cpu, Mainboard mainboard) {
        //310
        if ((cpu.getSocket() & mainboard.getCpuSocket()) != 1) {
            errorMsg.add(new ServiceValidationResponse("메인보드와 cpu 소켓이 일치하지 않음", "", 1));
        }

        //311
        if (mainboard.getVcore() != 0) {
            double score;
            score = (double) cpu.getTdp() / mainboard.getVcore() * 0.35;
            if (score > 1 && score < 1.1) {
                errorMsg.add(new ServiceValidationResponse("메인 보드가 CPU의 전력 소모량을 감당하기 힘듬", "", 0));;
            }
            else if (score >= 1.1) {
                errorMsg.add(new ServiceValidationResponse("메인 보드가 CPU의 전력 소모량을 감당할 수 없음", "",1));
            }
        }
        else if (mainboard.getPowerPhase() != 0){
            double score;
            double vscore = mainboard.getPowerPhase() * 50;
            score = (double) cpu.getTdp() / vscore * 0.35;
            if (score > 1 && score < 1.1) {
                errorMsg.add(new ServiceValidationResponse("메인 보드가 CPU의 전력 소모량을 감당하기 힘듬",  "",0));;
            }
            else if (score >= 1.1) {
                errorMsg.add(new ServiceValidationResponse("메인 보드가 CPU의 전력 소모량을 감당할 수 없음", "",1));
            }
        }
        else {
            errorMsg.add(new ServiceValidationResponse("메인보드와 CPU의 전력 소모량을 확인할 수 없음", "", 0));
        }
    }
}
