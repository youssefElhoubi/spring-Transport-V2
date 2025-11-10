package com.transports.transport.service;

import com.transports.transport.DTOS.CustomerDTO;
import com.transports.transport.MapperImplementation.CustomerMapperImpl;
import com.transports.transport.entities.Customer;
import com.transports.transport.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapperImpl customerMapperImpl;

    @Autowired
    public CustomerService(CustomerRepository customerRepository , CustomerMapperImpl customerMapperImpl) {
        this.customerRepository = customerRepository;
        this.customerMapperImpl = customerMapperImpl;
    }
    public CustomerDTO add(CustomerDTO customer){
        Customer entity = customerMapperImpl.toEntity(customer);
        entity = customerRepository.save(entity);
        customer = customerMapperImpl.toDTO(entity);
        return customer;
    }
    public CustomerDTO update(CustomerDTO customer){
        Customer entity = customerMapperImpl.toEntity(customer);
        entity = customerRepository.save(entity);
        customer = customerMapperImpl.toDTO(entity);
        return customer;
    }

}
