package com.telran.trackingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "customer")

public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany
    List<ShipmentEntity> shipments;

    public CustomerEntity(String firstName, String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public CustomerEntity(Long customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}
