package com.example.intelligence.domain.hardware;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mainboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String imageUrl;
    private String ramSocket;
    private byte supportedBoardFormFactors;
    private int vcore;
    private int powerPhase;
    private int supportedramSpeed;
    private int supportedramSlotCount;
    private int supportedmaxMemory;
    private int m2SlotCount;
    private int sataSlotCount;
    private byte cpuSocket;

    public Mainboard(Long id, String name, String imageUrl, String ramSocket, byte supportedBoardFormFactors, int vcore, int powerPhase, int supportedramSpeed, int supportedramSlotCount, int supportedmaxMemory, int m2SlotCount, int sataSlotCount, byte cpuSocket1) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.ramSocket = ramSocket;
        this.supportedBoardFormFactors = supportedBoardFormFactors;
        this.vcore = vcore;
        this.powerPhase = powerPhase;
        this.supportedramSpeed = supportedramSpeed;
        this.supportedramSlotCount = supportedramSlotCount;
        this.supportedmaxMemory = supportedmaxMemory;
        this.m2SlotCount = m2SlotCount;
        this.sataSlotCount = sataSlotCount;
        this.cpuSocket = cpuSocket1;
    }
}
