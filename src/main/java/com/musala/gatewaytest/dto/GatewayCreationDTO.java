package com.musala.gatewaytest.dto;

import com.musala.gatewaytest.annotation.IpAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatewayCreationDTO implements Serializable {
    @NotBlank
    @NotNull
    private String serialNumber;
    @NotBlank
    @NotNull
    private String userName;
    @NotBlank
    @NotNull
    @IpAddress
    private String ipAddress;
}
