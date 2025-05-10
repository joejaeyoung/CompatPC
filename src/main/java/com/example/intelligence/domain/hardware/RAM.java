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

    private String imageUrl;
    private String socket;
    private int capacity;
    private int quantity;
    private int clockSpeed;

    @Builder
    public RAM(String name, String imageUrl, String socket, int capacity, int quantity, int clockSpeed) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.socket = socket;
        this.capacity = capacity;
        this.quantity = quantity;
        this.clockSpeed = clockSpeed;
    }
}
