package com.transports.transport.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class UserDto {
    public interface create {
    }

    public interface update {
    }

    @NotBlank(message = "name is required",groups = {create.class,update.class})
    private String name;
    @NotBlank(message = "address is required",groups = {create.class,update.class})
    private String address;
    @NotNull(message = "latitude is required",groups = {create.class,update.class})
    private Double latitude;
    @NotNull(message = "longitude is required",groups = {create.class,update.class})
    private Double longitude;
    @NotNull(message = "preferredTimeSlot is required",groups = {create.class,update.class})
    private LocalDateTime preferredTimeSlot;
}
