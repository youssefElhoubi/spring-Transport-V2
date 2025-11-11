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
    public interface create{}
    public interface update{}

    // --- RELATION IDs (instead of full objects) ---
    @NotNull(message = "Customer ID is required" , groups = {create.class, update.class})
    private Long customerId;

    @NotNull(message = "Tour ID is required", groups = {create.class, update.class})
    private Long tourId;

    // --- DELIVERY DATA ---
    @NotNull(message = "Date of delivery is required" , groups = {create.class})
    @PastOrPresent(message = "Date of delivery cannot be in the future" , groups = { update.class})
    private LocalDate date;

    @NotNull(message = "Planned time is required" , groups = {create.class, update.class})
    private LocalTime plannedTime;

    @NotNull(message = "Actual time is required" , groups = {create.class, update.class})
    private LocalTime actualTime;

    @PositiveOrZero(message = "Delay cannot be negative", groups = {create.class, update.class})
    private Long delay;

    @NotNull(message = "Day of the week is required", groups = {create.class,})
    private DayOfWeek dayOfWeek;
}
