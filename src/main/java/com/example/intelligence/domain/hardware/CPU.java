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

    private String imageUrl;
    private String socket;
    private boolean hasGPU;
    private boolean hasCooler;
    private int tdp;

    @Builder
    public CPU(String name, String imageUrl, String socket, boolean hasGPU, boolean hasCooler, int tdp) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.socket = socket;
        this.hasGPU = hasGPU;
        this.hasCooler = hasCooler;
        this.tdp = tdp;
    }
}
