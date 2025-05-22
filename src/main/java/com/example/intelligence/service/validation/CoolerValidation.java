package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.Cooler;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class CoolerValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    //820
    public void checkWithCase(ServiceUserRequest request, Cooler cooler, Cases cases) {

    }

}
