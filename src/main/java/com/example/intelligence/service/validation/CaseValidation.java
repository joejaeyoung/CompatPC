package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.PSU;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CaseValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    public void checkWithPsu(Cases cases, PSU psu) {
        if (psu.getLength() == -1) {
            errorMsg.add(new ServiceValidationResponse(
                    "파워 서플라이의 길이 정보를 확인할 수 없는 제품입니다.",
                    "케이스와 호환여부를 제조사에 문의하십시오.",
                    0));
            return;
        }
        //970
        if(psu.getLength() > cases.getMaxPsuLength()) {
            errorMsg.add(new ServiceValidationResponse("파워서플라이의 길이가 케이스의 장착 허용 길이를 초과합니다.", "선택한 파워서플라이의 길이 " + psu.getLength() + "mm는 케이스가 지원하는 최대 파워 장착 길이 " + cases.getMaxPsuLength() + "mm를 초과합니다.\n" +
                    "이로 인해 파워를 케이스 내부에 장착할 수 없습니다.\n" + "더 짧은 파워서플라이를 선택하거나,\n" + "파워 장착 길이에 여유가 있는 케이스로 변경해 주세요.", 1));
        }
        if (psu.getPcie16PinCount() == -1 && psu.getPcie8PinCount() == -1 && psu.getPcie6PinCount() == -1) {
            errorMsg.add(new ServiceValidationResponse(
                    "파워 서플라이의 그래픽카드 보조전원 포트 정보를 확인할 수 없는 제품입니다.",
                    "그래픽카드와 호환여부를 제조사에 문의하십시오.",
                    0));
            return;
        }
        //971
        if (cases.getSupportedPsuFormFactor().equals("표준-ATX") && psu.getFormFactor().equals("ATX 파워")) {
            // 체크리스트 971을 건너뛰고 아무 작업도 하지 않음
        }
        else if (cases.getSupportedPsuFormFactor().equals("M-ATX(SFX)") && psu.getFormFactor().equals("M-ATX(SFX) 파워")) {
            // 체크리스트 971을 건너뛰고 아무 작업도 하지 않음
        }
        else if (cases.getSupportedPsuFormFactor().equals("TFX") && psu.getFormFactor().equals("TFX 파워")) {
            // 체크리스트 971을 건너뛰고 아무 작업도 하지 않음
        }
        else if(!cases.getSupportedPsuFormFactor().equals(psu.getFormFactor())) {
            log.info("case {} psu {}", cases.getSupportedBoardFormFactors(), psu.getFormFactor());
            log.info("psu {}", psu.toString());
            errorMsg.add(new ServiceValidationResponse("케이스가 지원하는 파워 규격과 선택한 파워서플라이의 규격이 서로 다릅니다.", "선택하신 케이스는 " + cases.getSupportedPsuFormFactor() + " 규격만 지원하지만, 현재 구성된 파워서플라이는 " + psu.getFormFactor() + " 규격입니다.\n" +
                    "이로 인해 파워를 케이스에 장착할 수 없습니다.\n" + "케이스가 지원하는 규격과 동일한 파워서플라이로 교체하거나,\n" + "해당 파워 규격을 지원하는 케이스로 변경해 주세요.", 1));
        }
    }
}
