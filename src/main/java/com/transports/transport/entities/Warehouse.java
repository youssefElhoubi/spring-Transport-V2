package com.transports.transport.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "address")
    private String address;
    @Column(nullable = false, name = "latitude")
    private Double latitude;
    @Column(nullable = false, name = "longitude")
    private Double longitude;
    @Column(nullable = false, name = "openingHours")
    private LocalDateTime openingHours;

    public Warehouse() {
    }

    public Warehouse(Long id, String address, Double latitude, Double longitude, LocalDateTime openingHours) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openingHours = openingHours;
    }
}
