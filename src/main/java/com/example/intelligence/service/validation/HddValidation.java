package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.HDD;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class HddValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    public void checkWithCase(ServiceUserRequest request, Cases cases) {
        if(request.getHddCount() <= 0) {
            request.setHddCount(0);
            return;
        }
        //861
        if (request.getHddCount() > cases.getBay3_5Count()) {
            errorMsg.add(new ServiceValidationResponse("케이스에 3.5인치 HDD를 장착할 공간이 부족합니다.", "선택하신 HDD는 총 " + request.getHddCount() + "개이며, 현재 케이스에는 3.5인치 베이가 " + cases.getBay3_5Count() + "개만 제공됩니다.\n" +
                    "따라서 일부 HDD 장착이 불가능 합니다.\n" + "3.5인치 베이를 " + request.getHddCount() + "개 이상 제공하는 케이스로 변경하거나 HDD 수량을 줄여주세요.\n" +
                    "일부 케이스는 제조사에서 별도로 판매하는 스토리지파트를 장착하여 이 문제를 해결할 수 있습니다. 자세한 정보는 케이스 제조사에 문의하세요.", 1));
        }
    }
}
