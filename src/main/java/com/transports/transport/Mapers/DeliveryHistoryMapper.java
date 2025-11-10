package com.transports.transport.Mapers;

import com.transports.transport.DTOS.DelivaryDto;
import com.transports.transport.DTOS.DeliveryHistoryDTO;
import com.transports.transport.entities.Customer;
import com.transports.transport.entities.Delivery;
import com.transports.transport.entities.DeliveryHistory;
import com.transports.transport.entities.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryHistoryMapper {
    DeliveryHistoryMapper INSTANCE = Mappers.getMapper(DeliveryHistoryMapper.class);
    DeliveryHistory toEntity (DeliveryHistoryDTO dto, Customer customer, Tour tour);
    DeliveryHistoryDTO toDto (DeliveryHistory entity);
}
