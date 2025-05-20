package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.Mainboard;
import com.example.intelligence.domain.hardware.RAM;
import com.example.intelligence.service.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class RamValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    //820
    public void checkWithRam(ServiceUserRequest request) {
        if (request.getRamQunatatiy() >= 2) {
            errorMsg.add(new ServiceValidationResponse("Ram Qunatati", "",0));
        }
    }

}
