package com.example.intelligence.domain.hardware;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cooler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String imageUrl;

    private String coolerType;
    private byte supportedSockets;
    private float height;
    private String coolerGrade;
    private int coolerTDP;



    @Builder
    public Cooler(String name, String imageUrl, String coolerType, String coolerGrade, byte supportedSockets, float height, int coolerTDP) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.coolerType = coolerType;
        this.coolerGrade = coolerGrade;
        this.supportedSockets = supportedSockets;
        this.height = height;
        this.coolerTDP = coolerTDP;
    }
}

