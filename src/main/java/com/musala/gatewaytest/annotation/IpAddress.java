package com.musala.gatewaytest.annotation;

import com.musala.gatewaytest.validator.IpAddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IpAddressValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IpAddress {
    String message() default "Invalid ip address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
