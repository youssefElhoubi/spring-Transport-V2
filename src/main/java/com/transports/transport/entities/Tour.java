package com.transports.transport.entities;

import com.transports.transport.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicul vehicle;
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveries;
    @Column(nullable = false)
    private Double totalDistance;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Tour() {
    }

    public Tour(Long id, LocalDate date, Vehicul vehicle, List<Delivery> deliveries, Double totalDistance, DeliveryStatus status, Warehouse warehouse) {
        this.id = id;
        this.date = date;
        this.vehicle = vehicle;
        this.deliveries = deliveries;
        this.totalDistance = totalDistance;
        this.status = status;
        this.warehouse = warehouse;
    }
}
