package com.tel_ran.tracking_service.controller;


import com.tel_ran.tracking_service.dto.CustomerDto;
import com.tel_ran.tracking_service.dto.ShipmentDto;
import com.tel_ran.tracking_service.entity.CustomerEntity;
import com.tel_ran.tracking_service.entity.ShipmentEntity;
import com.tel_ran.tracking_service.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/customers")
@RestController
public class CustomerController {

    private CustomerService customerService;

    ModelMapper modelMapper=new ModelMapper();

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @GetMapping
    List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers()
                .stream()
                .map(c->modelMapper.map(c, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    CustomerDto getCustomerById(@PathVariable long id){

        CustomerEntity customerById = customerService.getCustomerById(id);
        CustomerDto customerDtoById = modelMapper.map(customerById, CustomerDto.class);
        return   customerDtoById;
    }
    @GetMapping("/{id}")
    List<ShipmentDto> getAllShipmentsByCustomerId(@PathVariable long customerId){
        List<ShipmentEntity> shipmentEntities = customerService.allShipments(customerId);
       return shipmentEntities.stream()
                        .map(s->modelMapper.map(s, ShipmentDto.class))
        .collect(Collectors.toList());
    }

    @PostMapping
    Long createNewCustomer(@RequestBody CustomerDto customerDto){
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        return customerEntity.getCustomer_id();
    }
    @PutMapping("/{id}")
    ResponseEntity updateCustomer(@RequestBody @Valid CustomerDto customerDto, @PathVariable long id){
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        customerService.update(customerEntity, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable long id){
        customerService.delete(id);
    }

}
