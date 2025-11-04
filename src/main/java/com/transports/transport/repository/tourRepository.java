package com.transports.transport.repository;

import com.transports.transport.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tourRepository extends JpaRepository<Tour, Long> {
}
