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

    private String imageUrl;
    private int required16PinCount;
    private int required8PinCount;
    private int required6PinCount;
    private int tdp;
    private int recommendedPsuOutput;
    private float length;
    private boolean hasLPbracket;

    @Builder
    public GPU(String name, String imageUrl, int required16PinCount, int required8PinCount, int required6PinCount, int tdp, int recommendedPsuOutput, float length, boolean hasLPbracket) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.required16PinCount = required16PinCount;
        this.required8PinCount = required8PinCount;
        this.required6PinCount = required6PinCount;
        this.tdp = tdp;
        this.recommendedPsuOutput = recommendedPsuOutput;
        this.length = length;
        this.hasLPbracket = hasLPbracket;
    }
}
