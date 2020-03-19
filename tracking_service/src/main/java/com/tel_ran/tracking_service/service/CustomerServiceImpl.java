package com.tel_ran.tracking_service.service;


import com.tel_ran.tracking_service.entity.CustomerEntity;
import com.tel_ran.tracking_service.entity.ShipmentEntity;
import com.tel_ran.tracking_service.repository.CustomerRepository;
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
        Long shipment_id = shipmentEntity.getShipment_id();
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

        customer.setCustomer_id(getCustomerById(id).getCustomer_id());
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
