package com.musala.gatewaytest.exception;

public class BadIdException extends Exception{
    public BadIdException() {
        super();
    }

    public BadIdException(String message) {
        super(message);
    }
}
