package com.musala.gatewaytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO implements Serializable {

    private String message;
    private Integer status;
    private Date timestamp;
}
