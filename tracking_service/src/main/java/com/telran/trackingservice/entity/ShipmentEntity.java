package com.telran.trackingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shipment")
public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Long shipmentId;
    @Column(name = "title")
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn//(name ="customer_id", insertable = false,updatable = false)
    private CustomerEntity customerId;
}
