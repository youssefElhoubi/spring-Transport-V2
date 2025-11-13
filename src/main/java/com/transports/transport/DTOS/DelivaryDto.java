package com.transports.transport.DTOS;

import com.transports.transport.enums.DeliveryStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelivaryDto {

    public interface Create {}
    public interface Update {}

    @NotBlank(groups = Create.class, message = "Address is required")
    private String address;

    @NotNull(message = "Customer ID is required", groups = {Create.class, Update.class})
    private Long customerId;

    @NotNull(groups = Create.class, message = "Weight is required")
    @Positive(message = "Weight must be positive", groups = {Create.class, Update.class})
    private Double weight;

    @NotNull(groups = Create.class, message = "Volume is required")
    @Positive(message = "Volume must be positive", groups = {Create.class, Update.class})
    private Double volume;

    @NotBlank(groups = Create.class, message = "Preferred time slot is required")
    private String preferredTimeSlot;

    @NotNull(groups = Create.class, message = "Status is required")
    private DeliveryStatus status;

    @NotNull(groups = Create.class, message = "Sequence order is required")
    @Min(value = 1, message = "Sequence order must be at least 1", groups = {Create.class, Update.class})
    private Integer sequenceOrder;

    @NotNull(groups = Create.class, message = "Tour ID is required")
    private Long tourId;
}
