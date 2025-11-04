package com.transports.transport.DTOS;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    public interface create {
    }

    public interface update {
    }

    @NotBlank(message = "name is required",groups = {create.class,update.class})
    private String name;
    @NotBlank(message = "address is required",groups = {create.class,update.class})
    private String address;
    @NotNull( groups = DelivaryDto.create.class , message = "latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be greater than or equal to -90" ,groups = {DelivaryDto.create.class, DelivaryDto.update.class})
    @DecimalMax(value = "90.0", message = "Latitude must be less than or equal to 90",groups = {DelivaryDto.create.class, DelivaryDto.update.class})
    private Double latitude;

    @NotNull( groups = DelivaryDto.create.class ,message = "Longitude is required" )
    @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180",groups = {DelivaryDto.create.class, DelivaryDto.update.class})
    @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180",groups = {DelivaryDto.create.class, DelivaryDto.update.class})
    private Double longitude;
    @NotNull(message = "preferredTimeSlot is required",groups = {create.class,update.class})
    private LocalDateTime preferredTimeSlot;
}
