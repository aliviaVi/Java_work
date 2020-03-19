package com.tel_ran.tracking_service.repository;

import com.tel_ran.tracking_service.entity.CustomerEntity;
import com.tel_ran.tracking_service.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity,Long> {
/*
    @Query("select s from ShipmentEntity as s join s.customerEntity customer where customer.id=:id")
    List<ShipmentEntity> getAllShipmentsByCustomerId(@Param("id") Long id);*/
}
