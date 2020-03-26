package com.telran.trackingservice.controller;


import com.telran.trackingservice.dto.CustomerDto;
import com.telran.trackingservice.dto.ShipmentDto;
import com.telran.trackingservice.entity.CustomerEntity;
import com.telran.trackingservice.entity.ShipmentEntity;
import com.telran.trackingservice.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController

public class CustomerController {

    private CustomerService customerService;

    ModelMapper modelMapper=new ModelMapper();

    @Autowired
    public CustomerController(@Qualifier(value = "customerServiceImpl") CustomerService customerService){
        this.customerService=customerService;
    }

    @GetMapping("/customers")
    List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers()
                .stream()
                .map(c->modelMapper.map(c, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/customers/{id}")
    CustomerDto getCustomerById(@PathVariable ("id") long id){

        CustomerEntity customerById = customerService.getCustomerById(id);
        CustomerDto customerDtoById = modelMapper.map(customerById, CustomerDto.class);
        return   customerDtoById;
    }
    @GetMapping("/customers/{id}")
    List<ShipmentDto> getAllShipmentsByCustomerId(@PathVariable("id") long customerId){
        List<ShipmentEntity> shipmentEntities = customerService.allShipments(customerId);
       return shipmentEntities.stream()
                        .map(s->modelMapper.map(s, ShipmentDto.class))
        .collect(Collectors.toList());
    }

    @PostMapping("/customers")
    Long createNewCustomer(@RequestBody CustomerDto customerDto){
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        return customerEntity.getCustomerId();
    }
    @PutMapping("/customers/{id}")
    ResponseEntity updateCustomer(@RequestBody @Valid CustomerDto customerDto, @PathVariable("id") long id){
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        customerService.update(customerEntity, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable ("id") long id){
        customerService.deleteById(id);
    }

}
