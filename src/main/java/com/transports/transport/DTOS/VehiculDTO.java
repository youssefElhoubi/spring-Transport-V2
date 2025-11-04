package com.transports.transport.DTOS;

import com.transports.transport.enums.VehicleType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculDTO {
    public interface create {
    }

    public interface update {
    }

    @NotNull(message = "Vehicle type is required")
    private VehicleType type;

    @NotNull(message = "Maximum weight is required", groups = create.class)
    @Positive(message = "Maximum weight must be positive", groups = {create.class, update.class})
    private Double maxWeight;

    @NotNull(message = "Maximum volume is required", groups = create.class)
    @Positive(message = "Maximum volume must be positive" , groups = {create.class, update.class})
    private Double maxVolume;

    @NotNull(message = "Maximum deliveries count is required", groups = create.class)
    @Positive(message = "Maximum deliveries must be positive" , groups = {create.class, update.class})
    private Double maxDeliveries;
}
