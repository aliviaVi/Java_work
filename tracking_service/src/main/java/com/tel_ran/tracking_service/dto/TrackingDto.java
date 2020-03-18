package com.tel_ran.tracking_service.dto;

import com.tel_ran.tracking_service.entity.StatusTracking;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackingDto {
     StatusTracking status;
    ShipmentDto shipment;
}
