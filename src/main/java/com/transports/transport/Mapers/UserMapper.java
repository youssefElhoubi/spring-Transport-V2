package com.transports.transport.Mapers;

import com.transports.transport.DTOS.UserDto;
import com.transports.transport.entities.Customer;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto (Customer user);
    Customer toEntity(UserDto dto);

}
