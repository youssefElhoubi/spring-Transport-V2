package com.transports.transport.entities;
import com.transports.transport.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "delivery")
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private Customer user;

    private Double weight;

    private Double volume;

    private String preferredTimeSlot;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Integer sequenceOrder;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;


}
