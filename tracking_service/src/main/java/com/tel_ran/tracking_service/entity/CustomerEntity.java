package com.tel_ran.tracking_service.entity;

import com.tel_ran.tracking_service.dto.ShipmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    private String firstName;
    private String lastName;
    @OneToMany
    List<ShipmentEntity> shipments;

    public CustomerEntity(String firstName, String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public CustomerEntity(Long customer_id,String firstName, String lastName) {
        this.customer_id = customer_id;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}
