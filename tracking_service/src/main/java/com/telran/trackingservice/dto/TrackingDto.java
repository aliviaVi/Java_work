package com.telran.trackingservice.dto;

import com.telran.trackingservice.entity.StatusTracking;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackingDto {
     StatusTracking status;
    ShipmentDto shipment;
}
