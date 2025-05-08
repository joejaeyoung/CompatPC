package com.example.intelligence.domain.hardware;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String img;
    private int powerConnectorType;
    private int powerConnectorCount;
    private int tdp;
    private float length;
    private float thickness;

    @Builder
    private GPU(String name, String img, int powerConnectorType, int powerConnectorCount, int tdp, float length, float thickness) {
        this.name = name;
        this.img = img;
        this.powerConnectorType = powerConnectorType;
        this.powerConnectorCount = powerConnectorCount;
        this.tdp = tdp;
        this.length = length;
        this.thickness = thickness;
    }
}
