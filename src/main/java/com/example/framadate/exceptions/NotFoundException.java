package com.example.framadate.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message + " not found");
    }
}
