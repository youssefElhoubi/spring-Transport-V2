package com.transports.transport.MapperImplementation;

import com.transports.transport.DTOS.CustomerDTO;
import com.transports.transport.Mapers.CustomerMapper;
import com.transports.transport.entities.Customer;
import com.transports.transport.entities.Delivery;
import com.transports.transport.entities.DeliveryHistory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setLatitude(dto.getLatitude());
        customer.setLongitude(dto.getLongitude());
        customer.setPreferredTimeSlot(dto.getPreferredTimeSlot());

        // If you only have IDs for deliveries, you can map them like this:
        if (dto.getDeliveriesIds() != null) {
            List<Delivery> deliveries = dto.getDeliveriesIds().stream()
                    .map(id -> {
                        Delivery d = new Delivery();
                        d.setId(id);
                        return d;
                    })
                    .collect(Collectors.toList());
            customer.setDeliveries(deliveries);
        }

        if (dto.getDeliveriesHistoryIds() != null) {
            List<DeliveryHistory> deliveriesHistory = dto.getDeliveriesHistoryIds().stream()
                    .map(id -> {
                        DeliveryHistory d = new DeliveryHistory();
                        d.setId(id);
                        return d;
                    })
                    .collect(Collectors.toList());
            customer.setDeliveriesHistory(deliveriesHistory);
        }

        return customer;
    }

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        dto.setLatitude(customer.getLatitude());
        dto.setLongitude(customer.getLongitude());
        dto.setPreferredTimeSlot(customer.getPreferredTimeSlot());

        if (customer.getDeliveries() != null) {
            dto.setDeliveriesIds(
                    customer.getDeliveries().stream()
                            .map(Delivery::getId)
                            .collect(Collectors.toList())
            );
        }

        if (customer.getDeliveriesHistory() != null) {
            dto.setDeliveriesHistoryIds(
                    customer.getDeliveriesHistory().stream()
                            .map(DeliveryHistory::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
