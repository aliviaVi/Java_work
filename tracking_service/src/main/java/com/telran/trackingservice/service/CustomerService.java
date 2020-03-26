package com.telran.trackingservice.service;

import com.telran.trackingservice.entity.CustomerEntity;
import com.telran.trackingservice.entity.ShipmentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    CustomerEntity create(CustomerEntity customer);

    void update(CustomerEntity customer, Long id);

    void deleteById(Long id);

    CustomerEntity getCustomerById(Long id);

    List<CustomerEntity> getAllCustomers();

    Long addShipment(ShipmentEntity shipmentEntity, Long customerId);

    List<ShipmentEntity> allShipments(Long customerId);

}
