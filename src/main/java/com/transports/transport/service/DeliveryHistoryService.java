package com.transports.transport.service;

import com.transports.transport.DTOS.DeliveryHistoryDTO;
import com.transports.transport.Mapers.DeliveryHistoryMapper;
import com.transports.transport.MapperImplementation.DeliveryHistoryMapperImpl;
import com.transports.transport.entities.Customer;
import com.transports.transport.entities.DeliveryHistory;
import com.transports.transport.entities.Tour;
import com.transports.transport.repository.CustomerRepository;
import com.transports.transport.repository.DeliveryHistoryRepository;
import com.transports.transport.repository.tourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryHistoryService {

    private final DeliveryHistoryRepository deliveryHistoryRepository;
    private final CustomerRepository customerRepository;
    private final tourRepository TourRepository;
    private final DeliveryHistoryMapperImpl mapper;

    // 🔹 CREATE
    public DeliveryHistoryDTO createDeliveryHistory(DeliveryHistoryDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + dto.getCustomerId()));

        Tour tour = TourRepository.findById(dto.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour not found with id " + dto.getTourId()));

        // Calculate delay automatically if not provided
        if (dto.getPlannedTime() != null && dto.getActualTime() != null) {
            long delay = Duration.between(dto.getPlannedTime(), dto.getActualTime()).toMinutes();
            dto.setDelay(delay);
        }

        DeliveryHistory history = mapper.toEntity(dto, customer, tour);
        DeliveryHistory saved = deliveryHistoryRepository.save(history);
        return mapper.toDto(saved);
    }

    // 🔹 READ ALL
    @Transactional(readOnly = true)
    public List<DeliveryHistoryDTO> getAllHistories() {
        return deliveryHistoryRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // 🔹 READ BY ID
    @Transactional(readOnly = true)
    public DeliveryHistoryDTO getHistoryById(Long id) {
        DeliveryHistory history = deliveryHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery history not found with id " + id));
        return mapper.toDto(history);
    }

    // 🔹 UPDATE
    public DeliveryHistoryDTO updateHistory(Long id, DeliveryHistoryDTO dto) {
        DeliveryHistory existing = deliveryHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery history not found with id " + id));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + dto.getCustomerId()));

        Tour tour = TourRepository.findById(dto.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour not found with id " + dto.getTourId()));

        existing.setCustomer(customer);
        existing.setTour(tour);
        existing.setDate(dto.getDate());
        existing.setPlannedTime(dto.getPlannedTime());
        existing.setActualTime(dto.getActualTime());
        existing.setDayOfWeek(dto.getDayOfWeek());

        // Recalculate delay
        if (dto.getPlannedTime() != null && dto.getActualTime() != null) {
            long delay = Duration.between(dto.getPlannedTime(), dto.getActualTime()).toMinutes();
            existing.setDelay(delay);
        }

        DeliveryHistory updated = deliveryHistoryRepository.save(existing);
        return mapper.toDto(updated);
    }

    // 🔹 DELETE
    public void deleteHistory(Long id) {
        DeliveryHistory history = deliveryHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery history not found with id " + id));
        deliveryHistoryRepository.delete(history);
    }
}
