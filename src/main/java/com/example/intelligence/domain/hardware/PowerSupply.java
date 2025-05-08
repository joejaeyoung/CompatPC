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
public class PowerSupply {
    /*
    @todo 다대다 관게 풀기
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img;
    @Column(unique = true)
    private String name;
    private int outputWattage;
    private List<Integer> powerConnectorTypes;
    private List<Integer> powerConnectorCounts;
    private int sataConnectorCount;

    @Builder
    private PowerSupply(String name, String img, int outputWattage, List<Integer> powerConnectorTypes, List<Integer> powerConnectorCounts) {
        this.name = name;
        this.img = img;
        this.outputWattage = outputWattage;
        this.powerConnectorTypes = powerConnectorTypes;
        this.powerConnectorCounts = powerConnectorCounts;
        this.sataConnectorCount = 0;
    }
}
