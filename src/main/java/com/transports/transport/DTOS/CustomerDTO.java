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
    public interface create{}
    public interface update{}

    @NotBlank(message = "Name is required", groups = {create.class})
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters" , groups = {create.class,update.class})
    private String name;

    @NotBlank(message = "Address is required" , groups = {create.class})
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters", groups = {create.class,update.class})
    private String address;

    @NotNull(message = "Latitude is required", groups = {create.class})
    @DecimalMin(value = "-90.0", message = "Latitude cannot be less than -90", groups = {create.class,update.class})
    @DecimalMax(value = "90.0", message = "Latitude cannot be greater than 90", groups = {create.class,update.class})
    private Double latitude;

    @NotNull(message = "Longitude is required", groups = {create.class})
    @DecimalMin(value = "-180.0", message = "Longitude cannot be less than -180", groups = {create.class,update.class})
    @DecimalMax(value = "180.0", message = "Longitude cannot be greater than 180", groups = {create.class,update.class})
    private Double longitude;

    @FutureOrPresent(message = "Preferred time slot must be in the present or future", groups = {create.class,update.class})
    private LocalDateTime preferredTimeSlot;

    // You usually don’t validate relationships in a DTO like this;
    // but if you want to include delivery IDs, use something like:
    private List<Long> deliveriesIds;

    private List<Long> deliveriesHistoryIds;
}
