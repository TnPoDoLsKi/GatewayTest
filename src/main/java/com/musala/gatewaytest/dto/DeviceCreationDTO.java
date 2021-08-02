package com.musala.gatewaytest.dto;

import com.musala.gatewaytest.enumerate.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCreationDTO implements Serializable {
    @NotNull
    @NotBlank
    private String vendor;

    @NotNull
    private DeviceStatus deviceStatus;
}
