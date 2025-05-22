package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.Cooler;
import com.example.intelligence.service.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class CoolerValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();


    //820
    public void checkWithCase(Cooler cooler, Cases cases) {

    }

}
