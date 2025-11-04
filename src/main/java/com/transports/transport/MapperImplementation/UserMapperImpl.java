package com.transports.transport.MapperImplementation;

import com.transports.transport.DTOS.UserDto;
import com.transports.transport.Mapers.UserMapper;
import com.transports.transport.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(Customer user) {
        return UserDto.builder()
                .name(user.getName())
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .address(user.getAddress())
                .preferredTimeSlot(user.getPreferredTimeSlot())
                .build();
    }
    @Override
    public Customer toEntity(UserDto userDto) {
        return Customer.builder()
                .name(userDto.getName())
                .latitude(userDto.getLatitude())
                .longitude(userDto.getLongitude())
                .address(userDto.getAddress())
                .preferredTimeSlot(userDto.getPreferredTimeSlot())
                .build();
    }

}
