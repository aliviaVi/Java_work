package com.telran.trackingservice.service;


import com.telran.trackingservice.entity.CustomerEntity;
import com.telran.trackingservice.entity.ShipmentEntity;
import com.telran.trackingservice.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public CustomerService employeeService(CustomerRepository customerRepository) {
            return new CustomerServiceImpl(customerRepository);
        }
    }
    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;


    @Test
    public  void should_create_customer(){

        CustomerEntity customerEntity=new CustomerEntity("ivan","petrov");

        CustomerEntity expectedCustomer=new CustomerEntity(1L,"ivan","petrov");

        when(customerRepository.save(customerEntity)).thenReturn(expectedCustomer);

        CustomerEntity customerEntityFromService = customerService.create(customerEntity);
        assertEquals(customerEntityFromService.getFirstName(),customerEntity.getFirstName());

        verify(customerRepository,times(1)).save(customerEntity);

    }

    @Test
    public void should_add_shipment_to_customer(){
        CustomerEntity customerEntity=new CustomerEntity(2L,"ivan","petrov");

        Long customer_id = customerEntity.getCustomerId();
        ShipmentEntity shipmentEntity=new ShipmentEntity(1L,"letter",customerEntity);
        List<ShipmentEntity> listShipment= new ArrayList<>();
        listShipment.add(shipmentEntity);

        customerEntity.setShipments(listShipment);
        when(customerRepository.getOne(customer_id)).thenReturn(customerEntity);
        Long shipmentIdFromService = customerService.addShipment(shipmentEntity, customer_id);

        assertEquals(shipmentEntity.getShipmentId(),shipmentIdFromService);
    }

    @Test
    public void should_return_all_customer_shipments(){
        CustomerEntity customerEntity=new CustomerEntity(2L,"ivan","petrov");

        Long customer_id = customerEntity.getCustomerId();
        ShipmentEntity shipmentEntity=new ShipmentEntity(1L,"letter",customerEntity);
        List<ShipmentEntity> listShipment= new ArrayList<>();
        listShipment.add(shipmentEntity);
        customerEntity.setShipments(listShipment);

        when(customerRepository.getOne(customer_id)).thenReturn(customerEntity);

        List<ShipmentEntity> shipmentFromService = customerService.allShipments(customer_id);
        assertEquals(listShipment,shipmentFromService);
    }

    @Test
    public void should_return_list_empty(){
        CustomerEntity customerEntity=new CustomerEntity(2L,"ivan","petrov");

        Long customer_id = customerEntity.getCustomerId();
        when(customerRepository.getOne(customer_id)).thenReturn(customerEntity);
        List<ShipmentEntity> shipmentEntities = customerService.allShipments(customer_id);

        assertNull(shipmentEntities);
    }
    @Test
    public void delete_customer_by_id_Test(){
        CustomerEntity customerEntity=new CustomerEntity(2L,"ivan","petrov");
        Long customer_id = customerEntity.getCustomerId();

        customerService.deleteById(customer_id);
        List<CustomerEntity> allCustomers = customerService.getAllCustomers();
        assertEquals(0,allCustomers.size());
        verify(customerRepository,times(1)).deleteById(customer_id);
    }

    @Test
    public void should_return_customer_by_id(){
        CustomerEntity customerEntity=new CustomerEntity(2L,"ivan","petrov");
        Long customer_id = customerEntity.getCustomerId();

        when(customerRepository.findById(customer_id)).thenReturn(Optional.of(customerEntity));
        CustomerEntity customerById = customerService.getCustomerById(customer_id);

        assertEquals(customerEntity,customerById);

    }
    @Test(expected = NoSuchElementException.class)
    public void should_return_ex_customer_with_wrong_id(){
        CustomerEntity customerEntity=new CustomerEntity(2L,"ivan","petrov");
        Long customer_id = customerEntity.getCustomerId();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customerEntity));
        CustomerEntity customerById = customerService.getCustomerById(customer_id);
        assertNotEquals(customer_id,customerById.getCustomerId());
    }

    @Test
    public void should_return_all_customers(){
        CustomerEntity customerEntity1=new CustomerEntity(2L,"ivan","petrov");
        CustomerEntity customerEntity2=new CustomerEntity(21L,"anna","mock");
        CustomerEntity customerEntity3=new CustomerEntity(23L,"petro","ivanov");

        List<CustomerEntity> allCustomers=Arrays.asList(customerEntity1,customerEntity2,customerEntity3);

        when(customerRepository.findAll()).thenReturn(allCustomers);

        List<CustomerEntity> allCustomersFromService = customerService.getAllCustomers();

        assertEquals(allCustomers,allCustomersFromService);


    }
}
