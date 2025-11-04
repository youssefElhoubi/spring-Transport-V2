package com.transports.transport.MapperImplementation;

import com.transports.transport.DTOS.VehiculDTO;
import com.transports.transport.Mapers.VehiculMapper;
import com.transports.transport.entities.Vehicul;
import org.springframework.stereotype.Component;

@Component
public class VehiculMapperImpl implements VehiculMapper {
    @Override
    public Vehicul toEntity(VehiculDTO dto) {
        Vehicul vehicul = new Vehicul();

        vehicul.setType(dto.getType());
        vehicul.setMaxWeight(dto.getMaxWeight());
        vehicul.setMaxVolume(dto.getMaxVolume());
        vehicul.setMaxDeliveries(dto.getMaxDeliveries());

        return vehicul;
    }

    @Override
    public VehiculDTO toDto(Vehicul entity) {
        VehiculDTO dto = new VehiculDTO();

        dto.setType(entity.getType());
        dto.setMaxWeight(entity.getMaxWeight());
        dto.setMaxVolume(entity.getMaxVolume());
        dto.setMaxDeliveries(entity.getMaxDeliveries());

        return dto;
    }
}
