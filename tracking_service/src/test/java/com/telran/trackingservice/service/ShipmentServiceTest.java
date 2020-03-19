package com.telran.trackingservice.service;

import com.telran.trackingservice.entity.CustomerEntity;
import com.telran.trackingservice.entity.ShipmentEntity;
import com.telran.trackingservice.repository.ShipmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ShipmentServiceTest {


    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ShipmentService employeeService(ShipmentRepository shipmentRepository) {
            return new ShipmentServiceImpl(shipmentRepository);
        }
    }

    @Autowired
    ShipmentService shipmentService;

    @MockBean
    ShipmentRepository shipmentRepository;

    @Test
    public void should_create_shipment(){
        CustomerEntity customerEntity=new CustomerEntity(2L,"ivan","petrov");

        ShipmentEntity shipmentEntity=new ShipmentEntity(1L,"letter", customerEntity);
        when(shipmentRepository.save(shipmentEntity)).thenReturn(shipmentEntity);

        ShipmentEntity shipmentEntityFromService = shipmentService.create(shipmentEntity);

        assertEquals(shipmentEntity,shipmentEntityFromService);
    }

    @Test
    public void should_return_shipment_by_id(){
        CustomerEntity customerEntity=new CustomerEntity(2L,"ivan","petrov");
        ShipmentEntity shipmentEntity=new ShipmentEntity(1L,"letter", customerEntity);
        Long shipment_id = shipmentEntity.getShipmentId();
        when(shipmentRepository.getOne(shipment_id)).thenReturn(shipmentEntity);

        ShipmentEntity shipmentById = shipmentService.getShipmentById(shipment_id);

        assertEquals(shipmentEntity,shipmentById);
    }
}
