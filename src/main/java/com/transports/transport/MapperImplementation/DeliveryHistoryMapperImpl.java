package com.transports.transport.MapperImplementation;

import com.transports.transport.DTOS.DeliveryHistoryDTO;
import com.transports.transport.entities.*;
import org.springframework.stereotype.Component;

@Component
public class DeliveryHistoryMapper {

    public DeliveryHistory toEntity(DeliveryHistoryDTO dto, Customer customer, Tour tour) {
        if (dto == null) return null;

        return DeliveryHistory.builder()
                .customer(customer)
                .tour(tour)
                .date(dto.getDate())
                .plannedTime(dto.getPlannedTime())
                .actualTime(dto.getActualTime())
                .delay(dto.getDelay())
                .dayOfWeek(dto.getDayOfWeek())
                .build();
    }

    public DeliveryHistoryDTO toDTO(DeliveryHistory entity) {
        if (entity == null) return null;

        return DeliveryHistoryDTO.builder()
                .customerId(entity.getCustomer().getId())
                .tourId(entity.getTour().getId())
                .date(entity.getDate())
                .plannedTime(entity.getPlannedTime())
                .actualTime(entity.getActualTime())
                .delay(entity.getDelay())
                .dayOfWeek(entity.getDayOfWeek())
                .build();
    }
}