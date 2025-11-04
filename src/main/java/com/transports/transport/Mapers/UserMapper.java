package com.transports.transport.Mapers;

import com.transports.transport.DTOS.UserDto;
import com.transports.transport.entities.User;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto (User user);
    User toEntity(UserDto dto);

}
