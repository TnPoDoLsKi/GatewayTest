package com.musala.gatewaytest.validator;

import com.musala.gatewaytest.annotation.IpAddress;
import org.apache.commons.validator.routines.InetAddressValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IpAddressValidator implements ConstraintValidator<IpAddress, String> {
    @Override
    public void initialize(IpAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String ipAddress, ConstraintValidatorContext constraintValidatorContext) {

        return InetAddressValidator.getInstance().isValidInet4Address(ipAddress);
    }
}
