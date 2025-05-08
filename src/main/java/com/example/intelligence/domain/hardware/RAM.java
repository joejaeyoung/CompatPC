package com.example.intelligence.domain.hardware;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RAM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String img;
    private String platformType;
    private String socketType;
    private int quantity;
    private int capacity;
    private int speed;

    @Builder
    private RAM(String name, String img, String platformType, String socketType, int quantity, int capacity, int speed) {
        this.name = name;
        this.img = img;
        this.platformType = platformType;
        this.socketType = socketType;
        this.quantity = quantity;
        this.capacity = capacity;
        this.speed = speed;
    }
}
