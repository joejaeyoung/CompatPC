package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.Mainboard;
import com.example.intelligence.domain.hardware.RAM;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainboardValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    //430 ~ 434
    public void checkWithRam( ServiceUserRequest request, Mainboard mainboard, RAM ram) {
        //430
        if (!Objects.equals(mainboard.getRamSocket(), ram.getSocket())) {
            errorMsg.add(new ServiceValidationResponse("메인보드와 RAM의 소켓이 서로 다릅니다.","선택하신 메인보드는 [메인보드 지원 메모리 규격] 메모리를 지원하며, 현재 선택한 RAM은 [RAM 규격]입니다. RAM의 규격이 메인보드와 일치하지 않아 장착이 불가능합니다. \n" +
                    "메인보드에 맞는 RAM 규격을 가진 제품으로 변경하거나 RAM에 맞는 메모리 규격을 지원하는 메인보드로 교체해 주세요.\n", 1));
        }

        //431
        //최대 메모리 속도랑 램 크기..?
        if (mainboard.getSupportedramSpeed() >= ram.getClockSpeed()) {
            errorMsg.add(new ServiceValidationResponse("RAM 속도가 메인보드의 지원 한계치를 초과합니다", "선택하신 RAM은 최대 [사용자 RAM 속도]MHz로 작동하지만, 현재 메인보드는 최대 [메인보드 지원 속도]MHz까지만 지원합니다. 이로 인해 RAM이 최대 속도로 동작하지 않고 메인보드에 맞춰 낮은 속도로 동작합니다. 이로 인해 메모리의 성능을 제대로 활용할 수 없습니다.\n" +
                    "부팅에는 문제가 없으나, 성능을 최대로 활용하려면 메인보드의 최대 지원 속도 이상을 지원하는 모델로 교체해주세요.\n" +
                    "또는 메인보드가 지원할 수 있는 속도의 메모리를 선택해주세요. \n",1));
        }

        //432
        if (mainboard.getSupportedmaxMemory() >= request.getRamCapacity() * request.getRamQunatatiy()) {
            errorMsg.add(new ServiceValidationResponse("RAM 총 용량이 메인보드 지원 한계를 초과합니다.", "선택하신 RAM의 총 용량은 [사용자 총 RAM 용량]GB이며, 현재 메인보드는 최대 [메인보드 지원 최대 용량]GB까지만 지원합니다. 이로 인해 일부 RAM이 인식되지 않거나 시스템이 정상적으로 동작하지 않을 수 있습니다.\n" +
                    "RAM의 용량 또는 개수를 줄여 총 용량을 메인보드 최대 지원 용량 이내로 맞춰 주세요.\n" +
                    "또는 더 높은 메모리 용량을 지원하는 메인보드로 교체해 주세요.\n",1));
        }

        //433
        if (mainboard.getSupportedramSlotCount() >= request.getRamQunatatiy()) {
            errorMsg.add(new ServiceValidationResponse("RAM 개수가 메인보드 RAM 슬롯 수를 초과합니다", "선택하신 RAM은 총 [사용자 RAM 개수]개이나, 현재 메인보드는 최대 [메인보드 RAM 슬롯 수]개 까지만 메모리를 장착할 수 있습니다. 이로 인해 일부 메모리를 장착할 수 없습니다.\n" +
                    "RAM 개수를 줄여 메인보드의 슬롯 수 이내로 맞춰 주세요.\n" +
                    "또는 더 많은 메모리 슬롯을 지원하는 메인보드로 변경해 주세요.\n",1));
        }

        //434
        if (mainboard.getSupportedmaxMemory() / mainboard.getSupportedramSlotCount() >= request.getRamCapacity()) {
            errorMsg.add(new ServiceValidationResponse("RAM 단일 용량이 메인보드 RAM 슬롯의 최대 단일 용량을 초과합니다.", "RAM 단일 용량이 메인보드 RAM 슬롯의 최대 단일 용량을 초과합니다\n" +
                    "선택하신 RAM은 개당 [사용자 RAM 용량]GB이나, 현재 메인보드는 슬롯당 최대 [슬롯당 최대 지원 용량]GB까지만 지원합니다. 이로 인해 RAM이 인식되지 않거나, 시스템이 정상적으로 작동하지 않을 수 있습니다.\n" +
                    "RAM 1개의 용량을 메인보드 슬롯당 최대 지원 용량 이하로 낮춰 주세요.\n" +
                    "또는 슬롯당 더 높은 용량을 지원하는 메인보드로 교체해 주세요.\n",1));
        }
    }

    //630
    public void checkWithSSD(Mainboard mainboard, ServiceUserRequest request) {
        if(request.getSsdId() == null) return; //전처리 추가 : 김도원
        if (mainboard.getM2SlotCount() < request.getM2ssdCount()) {
            errorMsg.add(new ServiceValidationResponse("M.2 SSD 개수가 메인보드 슬롯 수를 초과합니다","선택하신 M.2 SSD는 총 [사용자 SSD 개수]개이며, 현재 메인보드는 최대 [메인보드 M.2 슬롯 수]개까지만 장착할 수 있습니다. 이로 인해 일부 SSD를 설치할 수 없습니다.\n" +
                    "M.2 SSD 개수를 줄여 메인보드 슬롯 수 이내로 맞춰 주세요.\n" +
                    "또는 더 많은 M.2 슬롯을 지원하는 메인보드로 교체해 주세요.\n", 1));
        }
    }

    //730
    public void checkWithHDD(Mainboard mainboard, ServiceUserRequest request) {
        if(request.getSsdId() == null) return; //전처리 추가 : 김도원
        if (mainboard.getSataSlotCount() >= request.getHddCount() + request.getSatassdCount()) {
            errorMsg.add(new ServiceValidationResponse("SATA 포트 수가 저장장치 수보다 적습니다.","메인보드는 SATA 포트를 총 [sataSlotCount]개 지원하지만, 현재 구성된 SATA 저장장치(HDD + SATA SSD)의 총 수는 [satassdCount + hdd.count]개입니다. 이로 인해 일부 저장장치를 장착할 수 없습니다.\n" +
                    "저장장치 개수를 줄이거나, SATA 포트 수가 더 많은 메인보드로 변경해 주세요.\n", 1));
        }
    }

    //830
    public void checkWithCase(Mainboard mainboard, Cases cases) {
        if ((cases.getSupportedBoardFormFactors() & mainboard.getSupportedBoardFormFactors()) != 1) {
            errorMsg.add(new ServiceValidationResponse("Board form factors", "",0));
        }
    }

}
