package com.transports.transport.service;

import com.transports.transport.DTOS.UserDto;
import com.transports.transport.MapperImplementation.UserMapperImpl;
import com.transports.transport.entities.Customer;
import com.transports.transport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public final UserRepository userRepository;
    public final UserMapperImpl userMapperImpl;

    @Autowired
    public UserService(UserRepository userRepository, UserMapperImpl userMapperImpl) {
        this.userRepository = userRepository;
        this.userMapperImpl = userMapperImpl;
    }

    public List<UserDto> findAll() {
        List<Customer> users = userRepository.findAll();
        return users.stream().map(this.userMapperImpl::toDto).toList();
    }
    public UserDto findById(Long id) {
        Customer user = userRepository.findById(id).orElseThrow(()->
                new RuntimeException("user was not found ")
        );
        return this.userMapperImpl.toDto(user);
    }
    public UserDto addUser(UserDto userDto) {
        Customer user = userMapperImpl.toEntity(userDto);
        user = userRepository.save(user);
        return this.userMapperImpl.toDto(user);
    }

}
