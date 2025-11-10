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
    public final CustomerRepository customerRepository;
    public final CustomerMapperImpl customerMapperImpl;

    @Autowired
    public CustomerService(CustomerRepository CustomerRepository, CustomerMapperImpl customerMapperImpl) {
        this.customerRepository = CustomerRepository;
        this.customerMapperImpl = customerMapperImpl;
    }

    public List<CustomerDTO> findAll() {
        List<Customer> users = customerRepository.findAll();
        return users.stream().map(this.customerMapperImpl::toDTO).toList();
    }
    public CustomerDTO findById(Long id) {
        Customer user = customerRepository.findById(id).orElseThrow(()->
                new RuntimeException("user was not found ")
        );
        return this.customerMapperImpl.toDTO(user);
    }
    public CustomerDTO addUser(CustomerDTO CustomerDTO) {
        Customer user = customerMapperImpl.toEntity(CustomerDTO);
        user = customerRepository.save(user);
        return this.customerMapperImpl.toDTO(user);
    }
    public String deleteById(Long id) {
        customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("user was not found ")
        );
        customerRepository.deleteById(id);
        return "Customer with id " + id + " was deleted";
    }

    public CustomerDTO updateById(Long id, CustomerDTO CustomerDTO) {
        customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("user was not found ")
        );
        Customer user = customerMapperImpl.toEntity(CustomerDTO);
        user = customerRepository.save(user);
        return this.customerMapperImpl.toDTO(user);
    }


}
