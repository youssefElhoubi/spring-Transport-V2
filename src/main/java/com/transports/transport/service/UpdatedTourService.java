package com.transports.transport.service;

import com.transports.transport.entities.DeliveryHistory;
import com.transports.transport.entities.Tour;
import com.transports.transport.enums.DeliveryStatus;
import com.transports.transport.repository.DeliveryHistoryRepository;
import com.transports.transport.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class UpdatedTourService extends TourService {
    private DeliveryHistoryRepository deliveryHistoryRepository;

    @Autowired
    public UpdatedTourService(TourRepository tourRepository, DeliveryHistoryRepository deliveryHistoryRepository) {
        super(tourRepository);
        this.deliveryHistoryRepository = deliveryHistoryRepository;
    }
    @Override
    public Tour update(Tour t) {
        Tour tour = tourRepository.findById(t.getId())
                .orElseThrow(() -> new RuntimeException("Tour not found with ID: " + t.getId()));

        tour.setDate(t.getDate());
        tour.setDeliveries(t.getDeliveries());
        tour.setVehicle(t.getVehicle());
        tour.setStatus(t.getStatus());
        tour.setWarehouse(t.getWarehouse());
        tour.setTotalDistance(t.getTotalDistance());
        tour.setId(t.getId());
        if (t.getStatus().equals(DeliveryStatus.DELIVERED)){
            tour.getDeliveries().forEach( delivery ->{
                DeliveryHistory deliveryHistory =  DeliveryHistory.builder()
                        .tour(tour)
                        .customer(delivery.getUser())
                        .date(LocalDate.now())
                        // plannedTime: if you have it in Delivery object, use that; otherwise null
                        .plannedTime(null)
                        .actualTime(LocalTime.now())
                        .dayOfWeek(LocalDate.now().getDayOfWeek())
                        .build();
                deliveryHistoryRepository.save(deliveryHistory);
            });
        }
        return tourRepository.save(tour);
    }
}
