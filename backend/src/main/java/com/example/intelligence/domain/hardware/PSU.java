package com.example.intelligence.domain.hardware;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PSU {
    /*
    @todo 다대다 관게 풀기
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String imageUrl;
    private int output;
    private int pcie16PinCount;
    private int pcie8PinCount;
    private int pcie6PinCount;
    private int sataConnectorCount;
    private int ideConnectorCount;
    private  float length;
    private String FormFactor;

    @Builder
    public PSU(String name, String imageUrl, int output, int pcie16PinCount, int pcie8PinCount, int pcie6PinCount, int sataConnectorCount, int ideConnectorCount, float length, String formFactor) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.output = output;
        this.pcie16PinCount = pcie16PinCount;
        this.pcie8PinCount = pcie8PinCount;
        this.pcie6PinCount = pcie6PinCount;
        this.sataConnectorCount = sataConnectorCount;
        this.ideConnectorCount = ideConnectorCount;
        this.length = length;
        FormFactor = formFactor;
    }
}
