package com.musala.gatewaytest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.gatewaytest.enumerate.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeripheralDevice implements Serializable {
    private static final long serialVersionUID = 0;
    
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    private String vendor;

    @CreatedDate
    private Date createdDate;

    @NotNull
    private DeviceStatus deviceStatus;

    @JsonIgnore
    @ManyToOne
    private Gateway gateway;
}
