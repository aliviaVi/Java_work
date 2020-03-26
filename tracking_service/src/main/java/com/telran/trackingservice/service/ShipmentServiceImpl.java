package com.telran.trackingservice.service;

import com.telran.trackingservice.entity.ShipmentEntity;
import com.telran.trackingservice.repository.ShipmentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
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
    public List<ShipmentEntity> getAllShipmentsByCustomerId(Long customerId) {

        return null;
    }
}
