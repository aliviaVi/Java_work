package com.tel_ran.tracking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipmentDto {
    String title;
    CustomerDto customer;
}
