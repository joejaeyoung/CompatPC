package com.example.intelligence.service.validation.dto.validation;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.PSU;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class CaseValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    public void checkWithPsu(Cases cases, PSU psu) {
        //970
        if(psu.getLength() > cases.getMaxPsuLength()) {
            errorMsg.add(new ServiceValidationResponse("파워서플라이의 길이가 케이스의 장착 허용 길이를 초과합니다.", "선택한 파워서플라이의 길이 [파워 길이]mm는 케이스가 지원하는 최대 파워 장착 길이 [케이스 허용 길이]mm를 초과합니다.\n" +
                    "이로 인해 파워를 케이스 내부에 장착할 수 없습니다.\n" + "더 짧은 파워서플라이를 선택하거나,\n" + "파워 장착 길이에 여유가 있는 케이스로 변경해 주세요.", 1));
        }

        //971
        if(!cases.getSupportedPsuFormFactor().equals(psu.getFormFactor())) {
            errorMsg.add(new ServiceValidationResponse("케이스가 지원하는 파워 규격과 선택한 파워서플라이의 규격이 서로 다릅니다.", "선택하신 케이스는 [케이스 지원 파워 규격] 규격만 지원하지만, 현재 구성된 파워서플라이는 [파워 규격] 규격입니다.\n" +
                    "이로 인해 파워를 케이스에 장착할 수 없습니다.\n" + "케이스가 지원하는 규격과 동일한 파워서플라이로 교체하거나,\n" + "해당 파워 규격을 지원하는 케이스로 변경해 주세요.", 1));
        }
    }
}
