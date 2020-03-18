package com.tel_ran.tracking_service.service;

import com.tel_ran.tracking_service.dto.ShipmentDto;
import com.tel_ran.tracking_service.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentService {

    ShipmentEntity create(ShipmentEntity shipment);

    ShipmentEntity getShipmentById(Long id);

    List<ShipmentEntity> getAllShipmentsByCustomerId(Long id);
}
