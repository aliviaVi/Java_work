package com.tel_ran.tracking_service.service;

import com.tel_ran.tracking_service.entity.ShipmentEntity;
import com.tel_ran.tracking_service.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    private ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public ShipmentEntity create(ShipmentEntity shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public ShipmentEntity getShipmentById(Long id) {
        return shipmentRepository.getOne(id);
    }

    @Override
    public List<ShipmentEntity> getAllShipmentsByCustomerId(Long id) {
        return null;
    }
}
