package com.transports.transport.service;

import com.transports.transport.DTOS.CustomerDTO;
import com.transports.transport.MapperImplementation.CustomerMapperImpl;
import com.transports.transport.entities.Customer;
import com.transports.transport.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    public final CustomerRepository CustomerRepository;
    public final CustomerMapperImpl customerMapperImpl;

    @Autowired
    public CustomerService(CustomerRepository CustomerRepository, CustomerMapperImpl customerMapperImpl) {
        this.CustomerRepository = CustomerRepository;
        this.customerMapperImpl = customerMapperImpl;
    }

    public List<CustomerDTO> findAll() {
        List<Customer> users = CustomerRepository.findAll();
        return users.stream().map(this.customerMapperImpl::toDTO).toList();
    }
    public CustomerDTO findById(Long id) {
        Customer user = CustomerRepository.findById(id).orElseThrow(()->
                new RuntimeException("user was not found ")
        );
        return this.customerMapperImpl.toDTO(user);
    }
    public CustomerDTO addUser(CustomerDTO CustomerDTO) {
        Customer user = customerMapperImpl.toEntity(CustomerDTO);
        user = CustomerRepository.save(user);
        return this.customerMapperImpl.toDTO(user);
    }

}
