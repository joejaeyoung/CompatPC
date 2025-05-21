package com.example.intelligence.domain.hardware;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    private byte supportedBoardFormFactors;
    private int bay2_5Count;
    private int bay3_5Count;
    private float maxGpuLength;
    private  float maxCoolerHeight;
    private  float maxPsuLength;
    private  boolean supportsVerticalPCI;
    private  boolean isLPcase;


}
