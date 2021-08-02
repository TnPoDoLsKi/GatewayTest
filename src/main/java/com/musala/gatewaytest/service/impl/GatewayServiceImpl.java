package com.musala.gatewaytest.service.impl;

import com.musala.gatewaytest.dto.DeviceCreationDTO;
import com.musala.gatewaytest.dto.GatewayCreationDTO;
import com.musala.gatewaytest.dto.ResponseDTO;
import com.musala.gatewaytest.entity.Gateway;
import com.musala.gatewaytest.entity.PeripheralDevice;
import com.musala.gatewaytest.exception.BadIdException;
import com.musala.gatewaytest.exception.BadRequestException;
import com.musala.gatewaytest.repository.DeviceRepository;
import com.musala.gatewaytest.repository.GatewayRepository;
import com.musala.gatewaytest.service.GatewayService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GatewayServiceImpl implements GatewayService {

    private GatewayRepository gatewayRepository;

    private DeviceRepository deviceRepository;

    private ModelMapper modelMapper = new ModelMapper();

    Logger log =  LoggerFactory.getLogger(GatewayServiceImpl.class);

    public GatewayServiceImpl(GatewayRepository gatewayRepository, DeviceRepository deviceRepository) {
        this.gatewayRepository = gatewayRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Gateway create(GatewayCreationDTO gateway) throws BadRequestException {

        if (gatewayRepository.existsBySerialNumber(gateway.getSerialNumber()))
            throw new BadRequestException("Serial number already exist");

        Gateway gatewayEntity = modelMapper.map(gateway,Gateway.class);
        gatewayEntity = gatewayRepository.save(gatewayEntity);

        log.trace("Gateway created with id: " + gatewayEntity.getId());
        return gatewayEntity;
    }

    @Override
    public Gateway getById(Long id) throws BadIdException {

        Optional<Gateway> gateway = gatewayRepository.findById(id);

        if (gateway.isEmpty())
            throw new BadIdException("Gateway id: "+id+" not found");

        log.trace("Gateway with id: "+id+"retrieved successfully");

        return gateway.get();
    }

    @Override
    public Page<Gateway> getAll(Integer page, Integer limit) {

        Page<Gateway> gateways =  gatewayRepository.findAll(PageRequest.of(page,limit));

        log.trace("All gateways retrieved");

        return gateways;
    }

    @Override
    public PeripheralDevice addDevice(Long id, DeviceCreationDTO deviceCreationDTO) throws BadIdException, BadRequestException {

        if (deviceRepository.countByGatewayId(id) == 10 )
            throw new BadRequestException("The gateway achieved the limit devices");

        Gateway gateway = getById(id);
        PeripheralDevice peripheralDevice = modelMapper.map(deviceCreationDTO, PeripheralDevice.class);
        peripheralDevice.setGateway(gateway);
        deviceRepository.save(peripheralDevice);

        return peripheralDevice;
    }

    @Override
    public ResponseDTO deleteDevice(Long deviceId) throws BadIdException {
        if (!deviceRepository.existsById(deviceId))
            throw new BadIdException("Device id not found");

        deviceRepository.deleteById(deviceId);
        return new ResponseDTO("Device deleted successfully",200,new Date(System.currentTimeMillis()));
    }
}
