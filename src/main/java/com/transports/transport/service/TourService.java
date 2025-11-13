package com.transports.transport.service;

import com.transports.transport.entities.Delivery;
import com.transports.transport.entities.Tour;
import com.transports.transport.repository.DeliveryRepository;
import com.transports.transport.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {
    protected TourRepository tourRepository;
    private DeliveryRepository deliveryRepository;

    @Autowired
    public TourService(TourRepository tourRepository, DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
        this.tourRepository = tourRepository;
    }
    // Get all tours
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    // Find a tour by ID
    public Tour findById(Long id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found with ID: " + id));
    }

    // Save a new tour
    public Tour save(Tour t) {
        return tourRepository.save(t);
    }

    // Update an existing tour
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



        return tourRepository.save(tour);
    }

    // Optional: Delete a tour by ID
    public void deleteById(Long id) throws RuntimeException {
        if (!tourRepository.existsById(id)) {
            throw new RuntimeException("Tour not found with ID: " + id);
        }
        tourRepository.deleteById(id);
    }
    public void AddDelivaries(List<Long> delivaries,Long tourID) {
        List<Delivery> deliveries = delivaries.stream().map(d->deliveryRepository.findById(d).orElseThrow( ()-> new RuntimeException("delivarie with Id "+d+" was not found") )).toList();
        Tour tour = findById(tourID);
        tour.getDeliveries().addAll(deliveries);
        tourRepository.save(tour);
    }

}
