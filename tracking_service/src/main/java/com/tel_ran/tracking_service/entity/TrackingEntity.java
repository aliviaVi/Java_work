package com.tel_ran.tracking_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class TrackingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tracking_id;

    private StatusTracking statusTracking;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn//(name = "shipment_id")
    private ShipmentEntity shipmentId;
}


