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
    private int clockSpeed;

    @Builder
    public RAM(String name, String imageUrl, String socket, int clockSpeed) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.socket = socket;
        this.clockSpeed = clockSpeed;
    }
}
