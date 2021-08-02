package com.musala.gatewaytest.repository;

import com.musala.gatewaytest.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayRepository extends JpaRepository<Gateway,Long> {
    boolean existsBySerialNumber(String serialNumber);
}
