package com.example.intelligence.service.dto.validation;

import lombok.Getter;

@Getter
public class ServiceUserRequest {
    Long cpuId;
    Long coolerId;
    Long mainboardId;
    Long ramId;
    int ramQunatatiy;
    int ramCapacity;
    Long gpuId;
    Long ssdId;
    int m2ssdCount;
    int satassdCount;
    Long hddId;
    int hddCount;
    Long caseId;
    Long psuId;

    public ServiceUserRequest(Long cpuId, Long coolerId, Long mainboardId, Long ramId, int ramQunatatiy, int ramCapacity, Long gpuId, Long ssdId, int m2ssdCount, int satassdCount, Long hddId, int hddCount, Long caseId, Long psuId) {
        this.cpuId = cpuId;
        this.coolerId = coolerId;
        this.mainboardId = mainboardId;
        this.ramId = ramId;
        this.ramQunatatiy = ramQunatatiy;
        this.ramCapacity = ramCapacity;
        this.gpuId = gpuId;
        this.ssdId = ssdId;
        this.m2ssdCount = m2ssdCount;
        this.satassdCount = satassdCount;
        this.hddId = hddId;
        this.hddCount = hddCount;
        this.caseId = caseId;
        this.psuId = psuId;
    }
}
