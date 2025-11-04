package com.transports.transport.MapperImplementation;

import com.transports.transport.DTOS.TourDto;
import com.transports.transport.Mapers.TourMapper;
import com.transports.transport.entities.Tour;
import com.transports.transport.entities.Vehicul;
import com.transports.transport.entities.Warehouse;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TourMapperImpl implements TourMapper {
    @Override
    public Tour toEntity(TourDto dto) {
        Tour tour = new Tour();

        tour.setDate(dto.getDate());
        tour.setTotalDistance(dto.getTotalDistance());
        tour.setStatus(dto.getStatus());

        // Map vehicleId to a Vehicul reference
        if (dto.getVehicleId() != null) {
            Vehicul vehicle = new Vehicul();
            vehicle.setId(dto.getVehicleId());
            tour.setVehicle(vehicle);
        }

        // Map warehouseId to a Warehouse reference
        if (dto.getWarehouseId() != null) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(dto.getWarehouseId());
            tour.setWarehouse(warehouse);
        }

        // Leave deliveries empty; actual mapping should be done in service
        tour.setDeliveries(Collections.emptyList());

        return tour;
    }

    @Override
    public TourDto toDto(Tour entity) {
        TourDto dto = new TourDto();

        dto.setDate(entity.getDate());
        dto.setTotalDistance(entity.getTotalDistance());
        dto.setStatus(entity.getStatus());

        if (entity.getVehicle() != null) {
            dto.setVehicleId(entity.getVehicle().getId());
        }

        if (entity.getWarehouse() != null) {
            dto.setWarehouseId(entity.getWarehouse().getId());
        }

        // Map deliveries to IDs
        if (entity.getDeliveries() != null && !entity.getDeliveries().isEmpty()) {
            dto.setDeliveryIds(entity.getDeliveries().stream()
                    .map(d -> d.getId())
                    .toList());
        }

        return dto;
    }
}
