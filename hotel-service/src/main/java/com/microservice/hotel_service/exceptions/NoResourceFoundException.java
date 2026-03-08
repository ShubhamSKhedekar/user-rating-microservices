package com.microservice.hotel_service.exceptions;

public class NoResourceFoundException extends RuntimeException {

    public NoResourceFoundException(String message) {    
        super(message);
    }

    public NoResourceFoundException() {
        super("No resource found!!");
    }
}
