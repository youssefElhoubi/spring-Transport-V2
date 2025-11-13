package com.transports.transport.MapperImplementation;

import com.transports.transport.DTOS.DelivaryDto;
import com.transports.transport.entities.Customer;
import com.transports.transport.entities.Delivery;
import com.transports.transport.entities.Tour;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapperImpl {

    public Delivery toEntity(DelivaryDto dto, Customer customer) {
        Delivery delivery = new Delivery();

        delivery.setAddress(dto.getAddress());
        delivery.setUser(customer);
        delivery.setWeight(dto.getWeight());
        delivery.setVolume(dto.getVolume());
        delivery.setPreferredTimeSlot(dto.getPreferredTimeSlot());
        delivery.setStatus(dto.getStatus());
        delivery.setSequenceOrder(dto.getSequenceOrder());

        // Map tourId to Tour object
        if (dto.getTourId() != null) {
            Tour tour = new Tour();
            tour.setId(dto.getTourId());
            delivery.setTour(tour);
        }

        return delivery;
    }

    public DelivaryDto toDto(Delivery entity) {
        DelivaryDto dto = new DelivaryDto();

        dto.setAddress(entity.getAddress());
        dto.setCustomerId(entity.getUser().getId());
        dto.setWeight(entity.getWeight());
        dto.setVolume(entity.getVolume());
        dto.setPreferredTimeSlot(entity.getPreferredTimeSlot());
        dto.setStatus(entity.getStatus());
        dto.setSequenceOrder(entity.getSequenceOrder());

        if (entity.getTour() != null) {
            dto.setTourId(entity.getTour().getId());
        }

        return dto;
    }
}
