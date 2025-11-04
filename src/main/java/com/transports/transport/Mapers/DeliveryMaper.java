package com.transports.transport.Mapers;

import com.transports.transport.DTOS.DelivaryDto;
import com.transports.transport.entities.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryMaper {
    DeliveryMaper INSTENCE = Mappers.getMapper(DeliveryMaper.class);

    Delivery toEntity (DelivaryDto dto);
    DelivaryDto toDto (Delivery entity);
}
