package com.transports.transport.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "delivery_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- RELATIONS ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    // --- DELIVERY DATA ---

    @Column(nullable = false)
    private LocalDate date; // delivery date (e.g., 2025-11-03)

    @Column(name = "planned_time")
    private LocalTime plannedTime; // expected delivery time

    @Column(name = "actual_time")
    private LocalTime actualTime; // real delivery time

    @Column(name = "delay_minutes")
    private Long delay; // actualTime - plannedTime (in minutes)

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", length = 10)
    private DayOfWeek dayOfWeek; // e.g., MONDAY, TUESDAY...

    // --- METHODS ---

    public void calculateDelay() {
        if (plannedTime != null && actualTime != null) {
            this.delay = Duration.between(plannedTime, actualTime).toMinutes();
        } else {
            this.delay = null;
        }
    }
}
