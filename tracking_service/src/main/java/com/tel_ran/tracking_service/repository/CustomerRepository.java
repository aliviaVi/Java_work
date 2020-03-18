package com.tel_ran.tracking_service.repository;

import com.tel_ran.tracking_service.dto.CustomerDto;
import com.tel_ran.tracking_service.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
