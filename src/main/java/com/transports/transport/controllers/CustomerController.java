package com.transports.transport.controllers;

import com.transports.transport.DTOS.CustomerDTO;
import com.transports.transport.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 🔹 Get All Customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    // 🔹 Get Customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO dto = customerService.findById(id);
        return ResponseEntity.ok(dto);
    }

    // 🔹 Create Customer
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(
            @Valid @RequestBody CustomerDTO dto
    ) {
        CustomerDTO created = customerService.addUser(dto);
        return ResponseEntity.ok(created);
    }

    // 🔹 Update Customer
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO dto
    ) {
        CustomerDTO updated = customerService.updateById(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Delete Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String message = customerService.deleteById(id);
        return ResponseEntity.ok(message);
    }
}
