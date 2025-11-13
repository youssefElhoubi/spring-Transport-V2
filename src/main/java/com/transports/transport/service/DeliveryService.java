package com.transports.transport.service;

import com.transports.transport.DTOS.DelivaryDto;
import com.transports.transport.MapperImplementation.DeliveryMapperImpl;
import com.transports.transport.entities.Customer;
import com.transports.transport.entities.Delivery;
import com.transports.transport.repository.CustomerRepository;
import com.transports.transport.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final CustomerRepository customerRepository;
    private  final DeliveryMapperImpl deliveryMapperImpl;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryrepository, CustomerRepository customerrepository, DeliveryMapperImpl deliveryMapperImpl) {
        this.customerRepository = customerrepository;
        this.deliveryRepository = deliveryrepository;
        this.deliveryMapperImpl = deliveryMapperImpl;
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Delivery findById(Long id) {
        return deliveryRepository.findById(id).orElseThrow(() -> new RuntimeException("no Delivery found with this ID" + id));
    }

    public Delivery save(DelivaryDto d) {
        Customer customer = customerRepository.findById(d.getCustomerId()).orElseThrow( () -> new RuntimeException("no Customer found with this ID" + d.getCustomerId()));
        Delivery delivery = deliveryMapperImpl.toEntity(d, customer);
        return deliveryRepository.save(delivery);
    }

    public Delivery update(Delivery d) {
        Delivery delivery = deliveryRepository.findById(d.getId()).orElseThrow(() -> new RuntimeException("Delivery not found with ID: " + d.getId()));
        delivery.setAddress(d.getAddress());
        delivery.setUser(d.getUser());
        delivery.setWeight(d.getWeight());
        delivery.setVolume(d.getVolume());
        delivery.setPreferredTimeSlot(d.getPreferredTimeSlot());
        delivery.setStatus(d.getStatus());
        delivery.setSequenceOrder(d.getSequenceOrder());
        delivery.setTour(d.getTour()); // if you want to allow updating the tour
        return deliveryRepository.save(delivery);
    }

    public void Delete(Long id) {
        deliveryRepository.deleteById(id);
    }
}
