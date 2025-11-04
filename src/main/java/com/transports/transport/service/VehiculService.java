package com.transports.transport.service;

import com.transports.transport.entities.Vehicul;
import com.transports.transport.repository.VehiculRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculService {
    private VehiculRepository vehiculRepository;
    @Autowired
    public VehiculService(VehiculRepository vehiculRepository) {
        this.vehiculRepository = vehiculRepository;
    }

    // Get all vehicles
    public List<Vehicul> findAll() {
        return vehiculRepository.findAll();
    }

    // Find a vehicle by ID
    public Vehicul findById(Long id) {
        return vehiculRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicul not found with ID: " + id));
    }

    // Save a new vehicle
    public Vehicul save(Vehicul v) {
        return vehiculRepository.save(v);
    }

    // Update an existing vehicle
    public Vehicul update(Vehicul v) {
        Vehicul vehicul = vehiculRepository.findById(v.getId())
                .orElseThrow(() -> new RuntimeException("Vehicul not found with ID: " + v.getId()));

        vehicul.setMaxDeliveries(v.getMaxDeliveries());
        vehicul.setMaxVolume(v.getMaxVolume());
        vehicul.setMaxWeight(v.getMaxWeight());
        vehicul.setType(v.getType());
        vehicul.setId(v.getId());

        return vehiculRepository.save(vehicul);
    }
    // Optional: Delete a vehicle by ID
    public void deleteById(Long id) {
        if (!vehiculRepository.existsById(id)) {
            throw new RuntimeException("Vehicul not found with ID: " + id);
        }
        vehiculRepository.deleteById(id);
    }
}
