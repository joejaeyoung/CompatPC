package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.Mainboard;
import com.example.intelligence.domain.hardware.RAM;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class MainboardValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    //430 ~ 434
    public void checkWithRam(Mainboard mainboard, RAM ram, ServiceUserRequest request) {
        //430
        if (mainboard.getRamSocket() != ram.getSocket()) {
            errorMsg.add(new ServiceValidationResponse("Ram, mainboard socket","", 1));
        }

        //431
        //최대 메모리 속도랑 램 크기..?
        if (mainboard.getSupportedramSpeed() >= request.getRamCapacity()) {
            errorMsg.add(new ServiceValidationResponse("Ram, mainboard supportedram", "",0));
        }

        //432
        if (mainboard.getSupportedmaxMemory() >= request.getRamQunatatiy() * request.getRamCapacity()) {
            errorMsg.add(new ServiceValidationResponse("Ram, mainboard supported", "",0));
        }

        //433
        if (mainboard.getSupportedramSlotCount() >= request.getRamCapacity()) {
            errorMsg.add(new ServiceValidationResponse("Ram, mainboard supported", "",0));
        }

        //434
        //최대 단일 램 용량?
        if (mainboard.getSupportedmaxMemory() >= request.getRamCapacity()) {
            errorMsg.add(new ServiceValidationResponse("Ram, mainboard supported", "",0));
        }
    }

    //630
    public void checkWithSSD(Mainboard mainboard, ServiceUserRequest request) {
        if (mainboard.getM2SlotCount() < request.getM2ssdCount()) {
            errorMsg.add(new ServiceValidationResponse("M2ssd, mainboard supported","", 0));
        }
    }

    //730
    public void checkWithHDD(Mainboard mainboard, ServiceUserRequest request) {
        if (mainboard.getSataSlotCount() < request.getHddCount() + request.getM2ssdCount()) {
            errorMsg.add(new ServiceValidationResponse("Hdd, mainboard supported","", 0));
        }
    }

    //830
    public void checkWithCase(Mainboard mainboard, Cases cases) {
        if ((cases.getSupportedBoardFormFactors() & mainboard.getSupportedBoardFormFactors()) != 1) {
            errorMsg.add(new ServiceValidationResponse("Board form factors", "",0));
        }
    }

}
