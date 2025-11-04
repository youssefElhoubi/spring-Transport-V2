package com.transports.transport.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transports.transport.enums.DeliveryStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TourDto {
    public interface create {
    }

    public interface update {
    }

    @NotNull(message = "Date is required", groups = create.class)
    @FutureOrPresent(message = "Tour date cannot be in the past", groups = {create.class, update.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Vehicle ID is required", groups = create.class)
    private Long vehicleId;

    @NotNull(message = "Warehouse ID is required", groups = create.class)
    private Long warehouseId;

    @NotNull(message = "Total distance is required", groups = create.class)
    @Positive(message = "Total distance must be positive", groups = {create.class, update.class})
    private Double totalDistance;

    @NotNull(message = "Status is required", groups = create.class)
    private DeliveryStatus status;

    // Optional: deliveries could be validated separately if provided
    private List<Long> deliveryIds;
}
