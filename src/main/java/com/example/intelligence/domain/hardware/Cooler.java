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
public class Cooler {
    /*
    @todo 다대다 관계 풀기
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String img;

    private String type;
    private List<String> supportedSockets;
    private float height;
    private float verticalLength;
    private int heatPipes;

    @Builder
    private Cooler(String name, String img, String type, List<String> supportedSockets, float height, float verticalLength, int heatPipes) {
        this.name = name;
        this.img = img;
        this.type = type;
        this.supportedSockets = supportedSockets;
        this.height = height;
        this.verticalLength = verticalLength;
        this.heatPipes = heatPipes;
    }
}

