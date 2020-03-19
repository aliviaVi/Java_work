package com.telran.trackingservice.service;

import com.telran.trackingservice.entity.StatusTracking;
import com.telran.trackingservice.entity.TrackingEntity;
import com.telran.trackingservice.repository.TrackingRepository;
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
