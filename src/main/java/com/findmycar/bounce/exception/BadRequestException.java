package com.findmycar.bounce.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends APIException {
    public BadRequestException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
