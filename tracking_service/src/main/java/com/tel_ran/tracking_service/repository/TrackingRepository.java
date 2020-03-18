package com.tel_ran.tracking_service.repository;

import com.tel_ran.tracking_service.entity.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {

}
