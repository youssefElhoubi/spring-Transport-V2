package com.transports.transport.controllers;

import com.transports.transport.DTOS.DeliveryHistoryDTO;
import com.transports.transport.service.DeliveryHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-history")
@RequiredArgsConstructor
public class DeliveryHistoryController {

    private final DeliveryHistoryService deliveryHistoryService;

    // 🔹 Create
    @PostMapping
    public ResponseEntity<DeliveryHistoryDTO> createHistory(
            @Valid @RequestBody DeliveryHistoryDTO dto
    ) {
        DeliveryHistoryDTO created = deliveryHistoryService.createDeliveryHistory(dto);
        return ResponseEntity.ok(created);
    }

    // 🔹 Get All
    @GetMapping
    public ResponseEntity<List<DeliveryHistoryDTO>> getAllHistories() {
        List<DeliveryHistoryDTO> histories = deliveryHistoryService.getAllHistories();
        return ResponseEntity.ok(histories);
    }

    // 🔹 Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryHistoryDTO> getHistoryById(@PathVariable Long id) {
        DeliveryHistoryDTO dto = deliveryHistoryService.getHistoryById(id);
        return ResponseEntity.ok(dto);
    }

    // 🔹 Update
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryHistoryDTO> updateHistory(
            @PathVariable Long id,
            @Valid @RequestBody DeliveryHistoryDTO dto
    ) {
        DeliveryHistoryDTO updated = deliveryHistoryService.updateHistory(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHistory(@PathVariable Long id) {
        deliveryHistoryService.deleteHistory(id);
        return ResponseEntity.ok("Delivery history with ID " + id + " was deleted successfully.");
    }
}
