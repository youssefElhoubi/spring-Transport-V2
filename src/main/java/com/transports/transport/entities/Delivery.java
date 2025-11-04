package com.transports.transport.entities;
import com.transports.transport.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Double latitude;

    private Double longitude;

    private Double weight;

    private Double volume;

    private String preferredTimeSlot;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Integer sequenceOrder;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    public Delivery(Long id, String address, Double latitude, Double longitude, Double weight, Double volume, String preferredTimeSlot, DeliveryStatus status, Integer sequenceOrder) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weight = weight;
        this.volume = volume;
        this.preferredTimeSlot = preferredTimeSlot;
        this.status = status;
        this.sequenceOrder = sequenceOrder;
    }

    public Delivery() {
    }
}
