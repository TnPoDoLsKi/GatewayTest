package com.musala.gatewaytest.controller;

import com.musala.gatewaytest.dto.DeviceCreationDTO;
import com.musala.gatewaytest.dto.GatewayCreationDTO;
import com.musala.gatewaytest.dto.ResponseDTO;
import com.musala.gatewaytest.entity.Gateway;
import com.musala.gatewaytest.entity.PeripheralDevice;
import com.musala.gatewaytest.exception.BadIdException;
import com.musala.gatewaytest.exception.BadRequestException;
import com.musala.gatewaytest.service.GatewayService;
import com.musala.gatewaytest.service.impl.GatewayServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/gateway")
@Log4j2
public class GatewayController {

    private GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping("")
    public ResponseEntity<Gateway> create(@Valid @RequestBody GatewayCreationDTO gateway) throws BadRequestException {
        log.trace("Gateway creating request");
        return ResponseEntity.status(HttpStatus.CREATED).body(gatewayService.create(gateway));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gateway> getById(@PathVariable Long id) throws BadIdException {
        return ResponseEntity.status(HttpStatus.CREATED).body(gatewayService.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<Gateway>> getAll(@RequestParam  Integer page, @RequestParam Integer limit){
        return ResponseEntity.status(HttpStatus.CREATED).body(gatewayService.getAll(page,limit));
    }

    @PostMapping("/{id}/device")
    public ResponseEntity<PeripheralDevice> addDevice(@PathVariable Long id, @Valid @RequestBody DeviceCreationDTO deviceCreationDTO) throws BadIdException, BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(gatewayService.addDevice(id, deviceCreationDTO));
    }


    @DeleteMapping("/device/{deviceId}")
    public ResponseEntity<ResponseDTO> addDevice(@PathVariable Long deviceId) throws BadIdException {
        return ResponseEntity.status(HttpStatus.CREATED).body(gatewayService.deleteDevice(deviceId));
    }

}
