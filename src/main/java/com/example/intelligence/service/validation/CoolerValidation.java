package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.CPU;
import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.Cooler;
import com.example.intelligence.domain.hardware.Mainboard;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CoolerValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    //210
    public void checkWithMainboard(ServiceUserRequest request, Cooler cooler, Mainboard board) {
        if ((cooler.getSupportedSockets() & board.getCpuSocket()) == 0) {
            errorMsg.add(new ServiceValidationResponse("쿨러가 메인보드의 CPU 소켓을 지원하지 않습니다.", "이로 인해 쿨러를 메인보드에 장착할 수 없습니다. \n" +
                    "현재 메인보드의 소켓을 지원하는 쿨러로 변경하거나, 해당 쿨러에 맞는 메인보드를 선택해 주세요.\n", 1));
        }
    }

    //211
    public void checkWithCPU(ServiceUserRequest request, Cooler cooler, CPU cpu) {
        double result;

        result = (double) cpu.getTdp() / request.getCoolerTdp();
        log.info("211번 result{}, cputdp{}, cooletdp{}", result, cpu.getTdp(), request.getCoolerTdp());
        if (result >= 1.15) {
            errorMsg.add(new ServiceValidationResponse("CPU의 발열이 쿨러의 성능보다 크게 높습니다.", "쿨링 성능이 심각하게 부족해 CPU의 성능이 제한되며 수명에 악영향을 줄 수 있습니다. 반드시 더 좋은 성능의 쿨러로 업그레이드하세요.", 1));
        }
        else if (result < 1.15 && result > 1.0) {
            errorMsg.add(new ServiceValidationResponse("CPU의 발열이 쿨러의 성능보다 약간 높습니다. ", "쿨링 성능이 부족해  가능성이 있습니다. 게이밍 용도로는 괜찮으나 작업 용도로 사용시 CPU의 성능이 제한됩니다. 더 좋은 성능의 쿨러로 업그레이드하는 것을 권장합니다.", 0));
        }
    }

    //220
    public void checkWithCPUCooler(ServiceUserRequest request, Cooler cooler, CPU cpu) {
        if (request.getCoolerId() == null && !cpu.isHasCooler()) {
            errorMsg.add(new ServiceValidationResponse("CPU에 쿨러가 포함되어 있지 않은 제품입니다.", "쿨러를 선택해주세요.", 1));
        }
    }

    //820
    public void checkWithCase(ServiceUserRequest request, Cooler cooler, Cases cases) {
        if ("공랭".equals(cooler.getCoolerType()) && cooler.getHeight() > cases.getMaxCoolerHeight()) {
            errorMsg.add(new ServiceValidationResponse(
                    "쿨러의 높이가 케이스의 지원 범위를 초과합니다.",
                    "선택하신 쿨러의 높이 [" + cooler.getHeight() + "]mm가 케이스가 지원하는 최대 CPU 쿨러 높이 [" + cases.getMaxCoolerHeight() + "]mm를 초과합니다.\n" +
                            "이로 인해 쿨러 장착이 불가능하거나 측면 패널이 닫히지 않을 수 있습니다.\n" +
                            "더 낮은 높이의 쿨러를 선택해주세요. 고성능 CPU를 사용하는 경우 3열 수랭 쿨러가 필요합니다.\n" +
                            "또는 더 높은 쿨러 높이를 지원하는 케이스를 선택해 주세요.",
                    1));
        }
    }
}
