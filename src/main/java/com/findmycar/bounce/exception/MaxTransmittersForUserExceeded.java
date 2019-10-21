package com.findmycar.bounce.exception;

import org.springframework.http.HttpStatus;

public class MaxTransmittersForUserExceeded extends APIException {
    public MaxTransmittersForUserExceeded(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
