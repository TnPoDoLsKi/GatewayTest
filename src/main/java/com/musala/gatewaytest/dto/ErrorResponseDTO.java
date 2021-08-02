package com.musala.gatewaytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO implements Serializable {
    private Date timestamp;
    private String message;
    private Integer Status;
    private String errors;
}
