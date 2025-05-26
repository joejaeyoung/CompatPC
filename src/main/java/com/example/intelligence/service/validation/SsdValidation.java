package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.SSD;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class SsdValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    public void checkWithCase(ServiceUserRequest request, SSD ssd, Cases cases) {
        if(request.getSsdId() == null) return;
        //860
        if(request.getSatassdCount() > cases.getBay2_5Count()) {
            errorMsg.add(new ServiceValidationResponse("케이스에 2.5인치 SATA SSD를 장착할 공간이 부족합니다.", "선택하신 SATA SSD는 총 {satassdCount}개이며, 현재 케이스에는 2.5인치 베이가 {bay2_5Count}개만 제공됩니다.\n" +
                    "따라서 일부 SSD 장착이 불가능 합니다.\n" + "2.5인치 베이를 {satassdCount}개 이상 제공하는 케이스로 변경하거나 SATA SSD 수량을 줄여주세요.\n" +
                    "3.5인치 베이에 장착 가능한 2.5인치 변환 어댑터 사용을 고려할 수 있으나, 장착 안정성이나 호환성에 문제가 발생할 수 있으므로 주의가 필요합니다.\n" +
                    "일부 케이스는 제조사에서 별도로 판매하는 스토리지파트를 장착하여 이 문제를 해결할 수 있습니다. 자세한 정보는 케이스 제조사에 문의하세요.", 1));
        }
    }
}
