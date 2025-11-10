package com.transports.transport.DTOS;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude cannot be less than -90")
    @DecimalMax(value = "90.0", message = "Latitude cannot be greater than 90")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude cannot be less than -180")
    @DecimalMax(value = "180.0", message = "Longitude cannot be greater than 180")
    private Double longitude;

    @FutureOrPresent(message = "Preferred time slot must be in the present or future")
    private LocalDateTime preferredTimeSlot;

    // You usually don’t validate relationships in a DTO like this;
    // but if you want to include delivery IDs, use something like:
    private List<Long> deliveriesIds;

    private List<Long> deliveriesHistoryIds;
}
