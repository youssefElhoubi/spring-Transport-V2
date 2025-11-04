package com.transports.transport.Mapers;

import com.transports.transport.DTOS.VehiculDTO;
import com.transports.transport.entities.Vehicul;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehiculMapper {
    VehiculMapper INSTENCE = Mappers.getMapper(VehiculMapper.class);

    Vehicul toEntity(VehiculDTO dto);
    VehiculDTO toDto(Vehicul vehicul );
}
