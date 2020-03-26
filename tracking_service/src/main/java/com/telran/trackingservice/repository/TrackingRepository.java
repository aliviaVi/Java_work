package com.telran.trackingservice.repository;

import com.telran.trackingservice.entity.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {

}
