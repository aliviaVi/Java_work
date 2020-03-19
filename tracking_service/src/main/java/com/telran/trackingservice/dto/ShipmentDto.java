package com.telran.trackingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipmentDto {
    String title;
    CustomerDto customer;
}
