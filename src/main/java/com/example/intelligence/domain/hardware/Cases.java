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
public class Cases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String imageUrl;
    private String supportedPsuFormFactor;
    private List<String> supportedBoardFormFactors;
    private int bay2_5Count;
    private int bay3_5Count;
    private float maxGpuLength;
    private  float maxCoolerHeight;
    private  float maxPsuLength;
    private  boolean supportsVerticalPCI;
    private  boolean isLPcase;

    @Builder
    public Cases(String name, String imageUrl, String supportedPsuFormFactor, List<String> supportedBoardFormFactors, int bay2_5Count, int bay3_5Count, float maxGpuLength, float maxCoolerHeight, float maxPsuLength, boolean supportsVerticalPCI, boolean isLPcase) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.supportedPsuFormFactor = supportedPsuFormFactor;
        this.supportedBoardFormFactors = supportedBoardFormFactors;
        this.bay2_5Count = bay2_5Count;
        this.bay3_5Count = bay3_5Count;
        this.maxGpuLength = maxGpuLength;
        this.maxCoolerHeight = maxCoolerHeight;
        this.maxPsuLength = maxPsuLength;
        this.supportsVerticalPCI = supportsVerticalPCI;
        this.isLPcase = isLPcase;
    }
}
