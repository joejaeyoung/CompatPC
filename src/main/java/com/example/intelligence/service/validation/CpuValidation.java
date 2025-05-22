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

    public void checkWithMainboard(CPU cpu, Mainboard mainboard) {
        //310
        if ((cpu.getSocket() & mainboard.getCpuSocket()) != 1) {
            errorMsg.add(new ServiceValidationResponse("CPU의 소켓과 메인보드의 소켓이 서로 다릅니다.", "현재 선택하신 CPU는 [CPU 소켓 종류] 소켓을 사용하며, 메인보드는 [메인보드 소켓 종류] 소켓을 지원합니다. 두 부품의 소켓 유형이 서로 달라 물리적으로 장착이 불가능합니다. \n" +
                    "CPU에 맞는 소켓을 지원하는 메인보드로 변경하거나, 메인보드에 맞는 소켓을 사용하는 CPU로 교체해 주세요.\n", 1));
        }

        //311
        if (mainboard.getVcore() != -1) {
            double score;
            score = (double) cpu.getTdp() / mainboard.getVcore() * 0.35;
            if (score > 1 && score < 1.1) {
                errorMsg.add(new ServiceValidationResponse("CPU의 전력요구량이 메인보드의 공급량보다 약간 높습니다.", "장시간 사용시 CPU의 성능저하를 유발할 가능성이 있습니다. 전원부가 더 좋은 제품으로 메인보드를 교체해주세요.", 0));;
            }
            else if (score >= 1.1) {
                errorMsg.add(new ServiceValidationResponse("CPU의 전력요구량이 메인보드의 공급량보다 매우 높습니다. ", "CPU의 성능이 제한되며 메인보드의 수명이 감소합니다. 반드시 전원부가 더 좋은 제품으로 메인보드를 교체해주세요.",1));
            }
        }
        else if (mainboard.getPowerPhase() != -1){
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
            errorMsg.add(new ServiceValidationResponse("전원부 정보를 확인할 수 없는 메인보드입니다.", "이 메인보드는 공식적으로 전원부 정보가 제공되지 않아, 고성능 CPU 사용 시 안정성을 검증할 수 없습니다. \n" +
                    "고성능 CPU를 사용할 예정이라면, 전원부 정보가 명확히 제공되는 메인보드를 선택해주세요.\n", 0));
        }
    }
}
