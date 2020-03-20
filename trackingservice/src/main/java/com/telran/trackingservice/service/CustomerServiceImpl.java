package com.telran.trackingservice.service;


import com.telran.trackingservice.entity.CustomerEntity;
import com.telran.trackingservice.entity.ShipmentEntity;
import com.telran.trackingservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

   private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Long addShipment(ShipmentEntity shipmentEntity, Long customerId) {
        CustomerEntity customer = customerRepository.getOne(customerId);
        List<ShipmentEntity> shipments = customer.getShipments();
        shipments.add(shipmentEntity);
        Long shipment_id = shipmentEntity.getShipmentId();
        return shipment_id;
    }

    @Override
    public List<ShipmentEntity> allShipments(Long customerId) {
        CustomerEntity customer = customerRepository.getOne(customerId);

        return customer.getShipments();
    }

    @Override
    public CustomerEntity create(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void update(CustomerEntity customer, Long id) {

        customer.setCustomerId(getCustomerById(id).getCustomerId());
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);

    }

    @Override
    public CustomerEntity getCustomerById(Long id) {
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        if(!byId.isPresent()) throw new NoSuchElementException("Customer with id: " + id + "is not found");

        return byId.get();
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }
}
