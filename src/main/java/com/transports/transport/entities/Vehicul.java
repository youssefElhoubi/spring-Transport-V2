package com.transports.transport.entities;

import com.transports.transport.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="vehicul")
public class Vehicul {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VehicleType type;
    @Column(name = "max_weight" ,nullable = false)
    private Double maxWeight;
    @Column(name = "max_volume" ,nullable = false)
    private Double maxVolume;
    @Column(name = "max_deliveries" ,nullable = false)
    private Double maxDeliveries;

    public Vehicul(Long id, VehicleType type, Double maxWeight, Double maxVolume, Double maxDeliveries) {
        this.id = id;
        this.type = type;
        this.maxWeight = maxWeight;
        this.maxVolume = maxVolume;
        this.maxDeliveries = maxDeliveries;
    }
    public Vehicul() {
    }
}
