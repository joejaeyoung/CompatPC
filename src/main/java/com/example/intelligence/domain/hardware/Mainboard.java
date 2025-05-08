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

    private String img;
    private String ramSocket;
    private String formFactor;
    private int vScore;
    private int powerPhase;
    private int ramSupportedSpeed;
    private int ramSlotCount;
    private int maxMemoryCapacity;
    private int m2SlotCount;
    private int sata3SlotCount;

    @Builder
    private Mainboard(String name, String img, String ramSocket, String formFactor, int vScore, int powerPhase, int ramSupportedSpeed, int ramSlotCount, int maxMemoryCapacity, int m2SlotCount, int sata3SlotCount) {
        this.name = name;
        this.img = img;
        this.ramSocket = ramSocket;
        this.formFactor = formFactor;
        this.vScore = vScore;
        this.powerPhase = powerPhase;
        this.ramSlotCount = ramSlotCount;
        this.ramSupportedSpeed = ramSupportedSpeed;
        this.maxMemoryCapacity = maxMemoryCapacity;
        this.m2SlotCount = m2SlotCount;
        this.sata3SlotCount = sata3SlotCount;
    }
}
