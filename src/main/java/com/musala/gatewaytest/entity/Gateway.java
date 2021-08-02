package com.musala.gatewaytest.entity;

import com.musala.gatewaytest.annotation.IpAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gateway implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
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

    @OneToMany(mappedBy = "gateway", cascade = {CascadeType.ALL})
    private List<PeripheralDevice> devices = new ArrayList<>();
}
