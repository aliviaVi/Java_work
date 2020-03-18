package com.tel_ran.tracking_service.service;

import com.tel_ran.tracking_service.dto.TrackingDto;
import com.tel_ran.tracking_service.entity.StatusTracking;
import com.tel_ran.tracking_service.entity.TrackingEntity;

import java.util.List;

public interface TrackingService {

    TrackingEntity create(TrackingEntity tracking);

    TrackingEntity getTrackingById(Long id);

    TrackingEntity getTrackingByShipmentId(Long id);

    StatusTracking getStatusById(Long id);


}
