package com.example.intelligence.service.validation.dto.validation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ServiceUserRequest {
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

    public ServiceUserRequest(Long cpuId, Long coolerId, int coolerTdp, Long mainboardId, Long ramId, int ramQunatatiy, int ramCapacity, Long gpuId, List<Long> ssdId, int m2ssdCount, int satassdCount, int hddCount, Long caseId, Long psuId) {
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
}
