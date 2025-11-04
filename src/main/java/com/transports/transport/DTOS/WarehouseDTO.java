package com.transports.transport.DTOS;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class WarehouseDTO{
    public interface create {
    }

    public interface update {
    }
    @NotBlank(message = "Address is required", groups = create.class)
    private String address;

    @NotNull(message = "Latitude is required" , groups = create.class)
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90", groups = {create.class, update.class})
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90", groups = {create.class, update.class})
    private Double latitude;

    @NotNull(message = "Longitude is required", groups = create.class)
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180", groups = {create.class, update.class})
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180", groups = {create.class, update.class})
    private Double longitude;

    @NotNull(message = "Opening hours must not be null", groups = create.class)
    @FutureOrPresent(message = "Opening hours cannot be in the past", groups = {create.class, update.class})
    private LocalDateTime openingHours;

}
