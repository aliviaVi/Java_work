package com.telran.trackingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tracking")
public class TrackingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "track_id")
    private Long trackingId;

    private StatusTracking statusTracking;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn//(name = "shipment_id")
    private ShipmentEntity shipmentId;
}


