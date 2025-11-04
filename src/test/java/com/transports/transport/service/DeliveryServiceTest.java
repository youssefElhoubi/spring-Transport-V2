package com.transports.transport.service;

import com.transports.transport.entities.Delivery;
import com.transports.transport.repository.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.beans.Expression;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {
    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    @Test
    void testFindById_found() {
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));
        Delivery result = deliveryService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
    @Test
    void testFindById_notFount(){
        Delivery delivery = new Delivery();
        when(deliveryRepository.findById(1L)).thenReturn(Optional.empty());
        Exception expression = assertThrows( RuntimeException.class,()->{
            deliveryService.findById(1l);
        });
        assertTrue(expression.getMessage().contains("no Delivery found with this ID"));
    }
    @Test
    void testFindAll() {
        Delivery d1 = new Delivery();
        d1.setId(1L);
        Delivery d2 = new Delivery();
        d2.setId(2L);

        when(deliveryRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Delivery> result = deliveryService.findAll();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(deliveryRepository, times(1)).findAll();
    }

}