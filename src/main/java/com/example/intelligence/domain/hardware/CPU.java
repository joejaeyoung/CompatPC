package com.example.intelligence.domain.hardware;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String img;
    private String socket;
    private boolean hasIntegratedGraphics;
    private boolean hasCooler;
    private int powerConsumption;

    @Builder
    private CPU(String name, String img, String socket, boolean hasIntegratedGraphics, boolean hasCooler, int powerConsumption) {
        this.name = name;
        this.img = img;
        this.socket = socket;
        this.hasIntegratedGraphics = hasIntegratedGraphics;
        this.hasCooler = hasCooler;
        this.powerConsumption = powerConsumption;
    }
}
