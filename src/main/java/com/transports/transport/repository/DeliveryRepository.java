package com.transports.transport.repository;

import com.transports.transport.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository  extends JpaRepository<Delivery,Long> {
}
