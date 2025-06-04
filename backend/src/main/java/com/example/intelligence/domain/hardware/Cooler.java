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
public class Cooler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String imageUrl;

    private String coolerType;
    private String coolerGrade;
    private byte supportedSockets;
    private float height;

    @Builder
    public Cooler(String name, String imageUrl, String coolerType, String coolerGrade, byte supportedSockets, float height) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.coolerType = coolerType;
        this.coolerGrade = coolerGrade;
        this.supportedSockets = supportedSockets;
        this.height = height;
    }
}

