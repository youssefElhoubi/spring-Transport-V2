package com.transports.transport.repository;

import com.transports.transport.entities.Vehicul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculRepository extends JpaRepository<Vehicul,Long> {
}
