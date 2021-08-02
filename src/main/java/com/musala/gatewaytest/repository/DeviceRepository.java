package com.musala.gatewaytest.repository;

import com.musala.gatewaytest.entity.PeripheralDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<PeripheralDevice, Long> {

    PeripheralDevice deletePeripheralDeviceById(Long deviceId);

    Integer countByGatewayId(Long id);
}
