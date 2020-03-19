package com.telran.trackingservice.service;

import com.telran.trackingservice.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentService {

    ShipmentEntity create(ShipmentEntity shipment);

    ShipmentEntity getShipmentById(Long id);

    List<ShipmentEntity> getAllShipmentsByCustomerId(Long id);
}
