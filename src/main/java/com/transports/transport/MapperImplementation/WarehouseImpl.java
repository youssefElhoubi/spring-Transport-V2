package com.transports.transport.MapperImplementation;

import com.transports.transport.DTOS.WarehouseDTO;
import com.transports.transport.Mapers.WarehouseMapper;
import com.transports.transport.entities.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseImpl implements WarehouseMapper {
    @Override
    public Warehouse toEntity(WarehouseDTO dto) {
        Warehouse warehouse = new Warehouse();

        warehouse.setAddress(dto.getAddress());
        warehouse.setLatitude(dto.getLatitude());
        warehouse.setLongitude(dto.getLongitude());
        warehouse.setOpeningHours(dto.getOpeningHours());

        return warehouse;
    }

    @Override
    public WarehouseDTO toDto(Warehouse entity) {
        WarehouseDTO dto = new WarehouseDTO();

        dto.setAddress(entity.getAddress());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setOpeningHours(entity.getOpeningHours());

        return dto;
    }
}
