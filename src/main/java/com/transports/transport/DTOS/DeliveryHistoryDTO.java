package com.transports.transport.DTOS;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryHistoryDTO {

    // --- RELATION IDs (instead of full objects) ---
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Tour ID is required")
    private Long tourId;

    // --- DELIVERY DATA ---
    @NotNull(message = "Date of delivery is required")
    @PastOrPresent(message = "Date of delivery cannot be in the future")
    private LocalDate date;

    @NotNull(message = "Planned time is required")
    private LocalTime plannedTime;

    @NotNull(message = "Actual time is required")
    private LocalTime actualTime;

    @PositiveOrZero(message = "Delay cannot be negative")
    private Long delay;

    @NotNull(message = "Day of the week is required")
    private DayOfWeek dayOfWeek;
}
