package com.musala.gatewaytest.config;

import com.musala.gatewaytest.dto.ErrorResponseDTO;
import com.musala.gatewaytest.exception.BadIdException;
import com.musala.gatewaytest.exception.BadRequestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class ExceptionCatcher {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.trace("MethodArgumentNotValidException thrown with message: " + ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(new Date(System.currentTimeMillis()),ex.getMessage(),HttpStatus.BAD_REQUEST.value(),errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }


    @ExceptionHandler(BadIdException.class)
    public ResponseEntity<ErrorResponseDTO> badIdExceptionHandling(BadIdException badIdException){
        log.trace("BadIdException thrown with message: " + badIdException.getMessage());
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(new Date(System.currentTimeMillis()),badIdException.getMessage(),HttpStatus.BAD_REQUEST.value(),"");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> badRequestExceptionHandling(BadRequestException badRequestException){
        log.trace("BadRequestException thrown with message: " + badRequestException.getMessage());
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(new Date(System.currentTimeMillis()),badRequestException.getMessage(),HttpStatus.BAD_REQUEST.value(),"");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }
}
