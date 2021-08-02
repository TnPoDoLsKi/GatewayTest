package com.musala.gatewaytest.unit.controller;

import com.musala.gatewaytest.dto.DeviceCreationDTO;
import com.musala.gatewaytest.dto.GatewayCreationDTO;
import com.musala.gatewaytest.entity.Gateway;
import com.musala.gatewaytest.entity.PeripheralDevice;
import com.musala.gatewaytest.enumerate.DeviceStatus;
import com.musala.gatewaytest.exception.BadIdException;
import com.musala.gatewaytest.exception.BadRequestException;
import com.musala.gatewaytest.repository.DeviceRepository;
import com.musala.gatewaytest.repository.GatewayRepository;
import com.musala.gatewaytest.service.GatewayService;
import com.musala.gatewaytest.service.impl.GatewayServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GatewayControllerTest {

    @Mock
    private GatewayRepository gatewayRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private GatewayServiceImpl gatewayService;

    @Test
    public void createTest() throws BadRequestException {

        GatewayCreationDTO gateway = new GatewayCreationDTO();
        gateway.setIpAddress("127.0.0.0");
        gateway.setSerialNumber(UUID.randomUUID().toString());
        gateway.setUserName("Test name");

        Gateway gateway1 = new Gateway() ;
        gateway1.setIpAddress(gateway.getIpAddress());
        gateway1.setSerialNumber(gateway.getSerialNumber());
        gateway1.setUserName(gateway.getUserName());

        when(gatewayRepository.save(ArgumentMatchers.any(Gateway.class))).thenReturn(gateway1);

        Gateway created = gatewayService.create(gateway);
        assertThat(created.getSerialNumber()).isSameAs(gateway1.getSerialNumber());
        verify(gatewayRepository).save(gateway1);

    }


    @Test(expected = BadIdException.class)
    public void getUserByIdTest() throws BadIdException {
        Gateway gateway = new Gateway();
        gateway.setId(1L);
        gateway.setIpAddress("127.0.0.0");
        gateway.setSerialNumber(UUID.randomUUID().toString());
        gateway.setUserName("Test name");
        given(gatewayRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        gatewayService.getById(gateway.getId());
    }

    @Test(expected = BadIdException.class)
    public void addDeviceTest() throws BadRequestException, BadIdException {

        DeviceCreationDTO device = new DeviceCreationDTO();
        device.setDeviceStatus(DeviceStatus.ONLINE);
        device.setVendor("Ousema");

        PeripheralDevice device1 = new PeripheralDevice();
        device1.setDeviceStatus(DeviceStatus.ONLINE);
        device1.setVendor("Ousema");


        PeripheralDevice created = gatewayService.addDevice(1L,device);
        assertThat(created.getVendor()).isSameAs(device1.getVendor());
        verify(deviceRepository).save(device1);

    }
}
