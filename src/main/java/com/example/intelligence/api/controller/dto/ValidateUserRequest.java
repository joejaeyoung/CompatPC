package com.example.intelligence.api.controller.dto;

import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ValidateUserRequest {
    Long cpuId;

    Long coolerId;
    int coolerTdp;

    Long mainboardId;

    Long ramId;
    int ramQunatatiy;
    int ramCapacity;

    Long gpuId;

    List<Long> ssdId;
    int m2ssdCount;
    int satassdCount;

    int hddCount;
    Long caseId;
    Long psuId;

    public ValidateUserRequest(Long cpuId, Long coolerId, int coolerTdp, Long mainboardId, Long ramId, int ramQunatatiy, int ramCapacity, Long gpuId, List<Long> ssdId, int m2ssdCount, int satassdCount, int hddCount, Long caseId, Long psuId) {
        this.cpuId = cpuId;
        this.coolerId = coolerId;
        this.coolerTdp = coolerTdp;
        this.mainboardId = mainboardId;
        this.ramId = ramId;
        this.ramQunatatiy = ramQunatatiy;
        this.ramCapacity = ramCapacity;
        this.gpuId = gpuId;
        this.ssdId = ssdId;
        this.m2ssdCount = m2ssdCount;
        this.satassdCount = satassdCount;
        this.hddCount = hddCount;
        this.caseId = caseId;
        this.psuId = psuId;
    }

    public ServiceUserRequest UserRuserRequesttoServiceUserRequest(Long cpuId, Long coolerId, int coolerTdp, Long mainboardId, Long ramId, int ramQunatatiy, int ramCapacity, Long gpuId, List<Long> ssdId, int m2ssdCount, int satassdCount, int hddCount, Long caseId, Long psuId) {
        return new ServiceUserRequest(cpuId, coolerId, coolerTdp, mainboardId, ramId, ramQunatatiy, ramCapacity, gpuId, ssdId, m2ssdCount, satassdCount, hddCount, caseId, psuId);
    }
}
