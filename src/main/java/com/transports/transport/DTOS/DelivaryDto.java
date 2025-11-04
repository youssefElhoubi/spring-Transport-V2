package com.transports.transport.DTOS;

import com.transports.transport.entities.Customer;
import com.transports.transport.enums.DeliveryStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelivaryDto {
    public interface create {
    }

    public interface update {
    }

    @NotBlank(groups = create.class, message = "address is required")
    private String address;

    @NotBlank(message = "user is required", groups = {create.class, update.class})
    private Customer user;

    @NotNull(groups = create.class, message = "Weight is required")
    @Positive(message = "Weight must be positive", groups = {create.class, update.class})
    private Double weight;

    @NotNull(groups = create.class, message = "Volume is required")
    @Positive(message = "Volume must be positive", groups = {create.class, update.class})
    private Double volume;

    @NotBlank(groups = create.class, message = "Preferred time slot is required")
    private String preferredTimeSlot;

    @NotNull(groups = create.class, message = "Status is required")
    private DeliveryStatus status;

    @NotNull(groups = create.class, message = "Sequence order is required")
    @Min(value = 1, message = "Sequence order must be at least 1", groups = {create.class, update.class})
    private Integer sequenceOrder;

    @NotNull(groups = create.class, message = "Tour ID is required")
    private Long tourId;
}
