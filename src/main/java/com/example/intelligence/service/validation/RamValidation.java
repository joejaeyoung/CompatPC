package com.example.intelligence.service.validation;

import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class RamValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    //440
    public void checkWithRam(ServiceUserRequest request) {
        if (request.getRamQunatatiy() <= 0) {
            errorMsg.add(new ServiceValidationResponse(
                    "RAM 개수가 유효하지 않습니다.",
                    "RAM 개수는 1 이상이어야 합니다.",
                    1));
            return;
        }
        if (request.getRamQunatatiy() == 1) {
            errorMsg.add(new ServiceValidationResponse(
                    "RAM 개수가 2개가 아닙니다",
                    "현재 선택하신 RAM은 1개만 구성되어 있어, 메인보드의 듀얼 채널 메모리 기능을 활용하지 못합니다. 이로 인해 메모리 대역폭과 성능이 감소합니다. 동일한 총 용량을 유지하기 위해 절반 용량의 RAM을 2개 구입하세요.",
                    0));
        } else if (request.getRamQunatatiy() >= 3) {
            errorMsg.add(new ServiceValidationResponse(
                    "RAM 개수가 2개가 아닙니다",
                    "RAM을 3개 이상 구성하면 XMP 오버클럭이 비활성화되거나, 메모리 속도가 낮아질 수 있습니다. 이로 인해 메모리 대역폭과 성능이 감소합니다.\n" +
                            "많은 용량의 RAM을 구매하고자 한다면, 메인보드가 지원하는 한도 내에서 RAM 단일 용량이 높은 제품을 선택해 RAM 개수를 2개로 맞춰주세요. 항상 짝수 개수의 RAM을 구입해야 합니다.\n",
                    0));
        }
    }

}
