package com.telran.trackingservice.repository;

import com.telran.trackingservice.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity,Long> {
/*
    @Query("select s from ShipmentEntity as s join s.customerEntity customer where customer.id=:id")
    List<ShipmentEntity> getAllShipmentsByCustomerId(@Param("id") Long id);*/
}
