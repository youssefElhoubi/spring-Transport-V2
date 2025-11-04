package com.transports.transport.controllers;

import com.transports.transport.DTOS.DelivaryDto;
import com.transports.transport.Mapers.DeliveryMaper;
import com.transports.transport.MapperImplementation.DeliveryMapperImpl;
import com.transports.transport.entities.Delivery;
import com.transports.transport.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
@Tag(name = "Delivery Management", description = "Endpoints for managing deliveries")
public class DeliveryController {

    private final DeliveryService deliverySer;
    private final DeliveryMaper deliveryMaper;
    @Autowired
    public DeliveryController(DeliveryService deliveryService, DeliveryMapperImpl deliveryMaper) {
        this.deliverySer = deliveryService;
        this.deliveryMaper = deliveryMaper;
    }

    // ------------------------ GET ALL ------------------------
    @Operation(
            summary = "Retrieve all deliveries",
            description = "Fetches all deliveries stored in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of deliveries retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Delivery.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public List<Delivery> all() {
        return deliverySer.findAll();
    }

    // ------------------------ CREATE ------------------------
    @Operation(
            summary = "Create a new delivery",
            description = "Creates and saves a new delivery in the system based on the provided data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Delivery.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<?> create(
            @Validated(DelivaryDto.create.class)
            @RequestBody(
                    description = "JSON object containing delivery data",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = DelivaryDto.class),
                            examples = @ExampleObject(
                                    name = "Delivery Example",
                                    value = """
                                    {
                                        "departure": "Casablanca",
                                        "destination": "Marrakech",
                                        "vehicleType": "CAR",
                                        "weight": 12.5
                                    }
                                    """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody DelivaryDto dto) {

        Delivery delivary = deliveryMaper.toEntity(dto);
        delivary = deliverySer.save(delivary);
        return ResponseEntity.ok(delivary);
    }

    // ------------------------ UPDATE ------------------------
    @Operation(
            summary = "Update an existing delivery",
            description = "Updates the delivery with the given ID using the provided data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Delivery.class))),
            @ApiResponse(responseCode = "404", description = "Delivery not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(
            @Validated(DelivaryDto.update.class)
            @RequestBody(
                    description = "JSON object containing updated delivery data",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = DelivaryDto.class),
                            examples = @ExampleObject(
                                    name = "Update Example",
                                    value = """
                                    {
                                        "destination": "Rabat",
                                        "weight": 15.0
                                    }
                                    """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody DelivaryDto dto,
            @PathVariable Long id) {

        Delivery delivery = deliverySer.findById(id);
        if (delivery == null) {
            return ResponseEntity.notFound().build();
        }
        delivery = deliveryMaper.toEntity(dto);
        delivery.setId(id);
        delivery = deliverySer.update(delivery);
        return ResponseEntity.ok(delivery);
    }

    // ------------------------ DELETE ------------------------
    @Operation(
            summary = "Delete a delivery",
            description = "Deletes a delivery from the system by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Delivery not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Delivery delivery = deliverySer.findById(id);
        if (delivery == null) {
            return ResponseEntity.notFound().build();
        }
        deliverySer.Delete(id);
        return ResponseEntity.ok("Delivery deleted successfully.");
    }
}
