package com.transports.transport.repository;

import com.transports.transport.entities.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {
}
