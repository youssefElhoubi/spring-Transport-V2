package com.transports.transport.Mapers;

import com.transports.transport.DTOS.CustomerDTO;
import com.transports.transport.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer toEntity(CustomerDTO customer);
    CustomerDTO toDTO(Customer customer);
}
