package com.telran.trackingservice.service;

import com.telran.trackingservice.entity.StatusTracking;
import com.telran.trackingservice.entity.TrackingEntity;

public interface TrackingService {

    TrackingEntity create(TrackingEntity tracking);

    TrackingEntity getTrackingById(Long id);

    TrackingEntity getTrackingByShipmentId(Long id);

    StatusTracking getStatusById(Long id);


}
