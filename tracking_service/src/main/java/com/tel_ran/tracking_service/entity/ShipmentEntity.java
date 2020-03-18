package com.tel_ran.tracking_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipment_id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="customer_id", insertable = false,updatable = false)
    private CustomerEntity customer_id;
}
