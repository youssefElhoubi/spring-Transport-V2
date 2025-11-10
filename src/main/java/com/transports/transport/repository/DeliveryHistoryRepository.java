package com.transports.transport.repository;

import com.transports.transport.entities.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory,Long> {
}
