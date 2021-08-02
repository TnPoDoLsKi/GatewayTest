package com.musala.gatewaytest.service;

import com.musala.gatewaytest.dto.DeviceCreationDTO;
import com.musala.gatewaytest.dto.GatewayCreationDTO;
import com.musala.gatewaytest.dto.ResponseDTO;
import com.musala.gatewaytest.entity.Gateway;
import com.musala.gatewaytest.entity.PeripheralDevice;
import com.musala.gatewaytest.exception.BadIdException;
import com.musala.gatewaytest.exception.BadRequestException;
import org.springframework.data.domain.Page;


public interface GatewayService {
     Gateway create(GatewayCreationDTO gateway) throws BadRequestException;

     Gateway getById(Long id) throws BadIdException;

     Page<Gateway> getAll(Integer page, Integer limit);

     PeripheralDevice addDevice(Long id, DeviceCreationDTO deviceCreationDTO) throws BadIdException, BadRequestException;

     ResponseDTO deleteDevice(Long deviceId) throws BadIdException;
}
