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

    private String img;

    private List<String> supportedBoardFormats;

    private float maxGpuLength;

    private float maxCoolerHeight;

    private float maxPsuLength;

    @Builder
    private Cases(String name, String img, float maxGpuLength, float maxCoolerHeight, float maxPsuLength) {
        this.name = name;
        this.img = img;
        this.maxGpuLength = maxGpuLength;
        this.maxCoolerHeight = maxCoolerHeight;
        this.maxPsuLength = maxPsuLength;
    }
}
