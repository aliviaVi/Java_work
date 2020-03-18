package com.tel_ran.tracking_service.service;

import com.tel_ran.tracking_service.entity.StatusTracking;
import com.tel_ran.tracking_service.entity.TrackingEntity;
import com.tel_ran.tracking_service.repository.TrackingRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackingServiceImpl implements TrackingService {

    private TrackingRepository trackingRepository;

    public TrackingServiceImpl(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    @Override
    public TrackingEntity create(TrackingEntity tracking) {
        return trackingRepository.save(tracking);
    }

    @Override
    public TrackingEntity getTrackingById(Long id) {
        return trackingRepository.getOne(id);
    }

    @Override
    public TrackingEntity getTrackingByShipmentId(Long id) {
        return null;
    }

    @Override
    public StatusTracking getStatusById(Long id) {
        return trackingRepository.getOne(id).getStatusTracking();
    }
}
