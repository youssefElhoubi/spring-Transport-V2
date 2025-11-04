package com.transports.transport.Mapers;

import com.transports.transport.DTOS.WarehouseDTO;
import com.transports.transport.entities.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseMapper {
    WarehouseMapper INSTENCE = Mappers.getMapper(WarehouseMapper.class);

    Warehouse toEntity(WarehouseDTO dto);
    WarehouseDTO toDto (Warehouse warehouse );
}
