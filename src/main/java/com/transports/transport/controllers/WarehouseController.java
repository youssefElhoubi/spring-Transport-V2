package com.transports.transport.controllers;

import com.transports.transport.DTOS.WarehouseDTO;
import com.transports.transport.MapperImplementation.WarehouseImpl;
import com.transports.transport.entities.Warehouse;
import com.transports.transport.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final WarehouseImpl warehouseMapper;

    @Autowired
    public WarehouseController(WarehouseService warehouseService, WarehouseImpl warehouse) {
        this.warehouseService = warehouseService;
        this.warehouseMapper = warehouse;
    }

    // ✅ Get all warehouses
    @GetMapping("/all")
    public List<Warehouse> all() {
        return warehouseService.findAll();
    }

    // ✅ Create a new warehouse
    @PostMapping
    public ResponseEntity<?> create(@Validated(WarehouseDTO.create.class) @RequestBody WarehouseDTO dto) {
        Warehouse warehouse = warehouseMapper.toEntity(dto);
        warehouse = warehouseService.save(warehouse);
        return ResponseEntity.ok(warehouse);
    }

    // ✅ Update an existing warehouse
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Validated(WarehouseDTO.update.class) @RequestBody WarehouseDTO dto, @PathVariable Long id) {
        Warehouse existingWarehouse = warehouseService.findById(id);
        if (existingWarehouse == null) {
            return ResponseEntity.notFound().build();
        }

        Warehouse warehouse = warehouseMapper.toEntity(dto);
        warehouse.setId(id);
        warehouse = warehouseService.update(warehouse);
        return ResponseEntity.ok(warehouse);
    }

    // ✅ Delete a warehouse by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.findById(id);
        if (warehouse == null) {
            return ResponseEntity.notFound().build();
        }

        warehouseService.deleteById(id);
        return ResponseEntity.ok("Warehouse deleted successfully");
    }
}
