package com.transports.transport.controllers;

import com.transports.transport.DTOS.VehiculDTO;
import com.transports.transport.MapperImplementation.VehiculMapperImpl;
import com.transports.transport.entities.Vehicul;
import com.transports.transport.service.VehiculService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/vehicul")
public class VehiculController {
    private final VehiculMapperImpl vehiculMapper;
    private final VehiculService vehiculService;

    @Autowired
    public VehiculController(VehiculService vehiculService, VehiculMapperImpl vehiculMapper) {
        this.vehiculService =vehiculService;
        this.vehiculMapper =  vehiculMapper;
    }
    @GetMapping("/all")
    public List<Vehicul> all() {
        return vehiculService.findAll();
    }

    // ✅ Create a new vehicul
    @PostMapping
    public ResponseEntity<?> create(@Validated(VehiculDTO.create.class) @RequestBody VehiculDTO dto) {
        Vehicul vehicul = vehiculMapper.toEntity(dto);
        vehicul = vehiculService.save(vehicul);
        return ResponseEntity.ok(vehicul);
    }

    // ✅ Update an existing vehicul
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Validated(VehiculDTO.update.class) @RequestBody VehiculDTO dto, @PathVariable Long id) {
        Vehicul existingVehicul = vehiculService.findById(id);
        if (existingVehicul == null) {
            return ResponseEntity.notFound().build();
        }

        Vehicul vehicul = vehiculMapper.toEntity(dto);
        vehicul.setId(id); // make sure the ID stays the same
        vehicul = vehiculService.update(vehicul);
        return ResponseEntity.ok(vehicul);
    }

    // ✅ Delete a vehicul by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Vehicul vehicul = vehiculService.findById(id);
        if (vehicul == null) {
            return ResponseEntity.notFound().build();
        }

        vehiculService.deleteById(id);
        return ResponseEntity.ok("Vehicul deleted successfully");
    }
}
