package com.tel_ran.tracking_service.service;

import com.tel_ran.tracking_service.dto.CustomerDto;
import com.tel_ran.tracking_service.entity.CustomerEntity;
import com.tel_ran.tracking_service.entity.ShipmentEntity;

import java.util.List;

public interface CustomerService {

    CustomerEntity create(CustomerEntity customer);

    void update(CustomerEntity customer, Long id);

    void deleteById(Long id);

    CustomerEntity getCustomerById(Long id);

    List<CustomerEntity> getAllCustomers();

    Long addShipment(ShipmentEntity shipmentEntity, Long customerId);

    List<ShipmentEntity> allShipments(Long customerId);

}
