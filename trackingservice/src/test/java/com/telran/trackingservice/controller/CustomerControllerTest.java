package com.telran.trackingservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.trackingservice.dto.CustomerDto;
import com.telran.trackingservice.entity.CustomerEntity;
import com.telran.trackingservice.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)

public class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;


    ModelMapper modelMapper=new ModelMapper();
    ObjectMapper objectMapper=new ObjectMapper();

    @Test
    public void should_return_all_customers() throws Exception {
        CustomerDto customerDto=new CustomerDto("ivan","petrov");
        CustomerDto customerDto1=new CustomerDto("anna","mock");

        CustomerEntity customerEntity1 = new CustomerEntity(1L, "ivan", "petrov", null);
        CustomerEntity customerEntity2 = new CustomerEntity(2L, "anna", "mock", null);

        List<CustomerEntity> customerEntityList=Arrays.asList(customerEntity1,customerEntity2);

        when(customerService.getAllCustomers()).thenReturn(customerEntityList);

        mockMvc.perform(get("/api/customers")
        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers").isArray())
                .andExpect(jsonPath("$.customers[0].customerId").value(1L))
                .andExpect(jsonPath("$.customers[0].firstName").value("ivan"))
                .andExpect(jsonPath("$.customers[0].lastName").value("petrov"))
                .andExpect(jsonPath("$.customers[0].shipments").value(null))

                .andExpect(jsonPath("$.customers[1].customerId").value(2L))
                .andExpect(jsonPath("$.customers[1].firstName").value("anna"))
                .andExpect(jsonPath("$.customers[1].lastName").value("mock"))
                .andExpect(jsonPath("$.customers[1].shipments").value(null));


    }

    @Test
    public void should_return_customer_by_id() throws Exception {
        CustomerDto customerDto=new CustomerDto("ivan","petrov");
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        customerEntity.setCustomerId(1L);

        when(customerService.getCustomerById(1L)).thenReturn(customerEntity);

        mockMvc.perform(get("/api/customers" + customerEntity.getCustomerId())
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName").value("ivan"))
                .andExpect(jsonPath("$.lastName").value("petrov"));
    }
}
