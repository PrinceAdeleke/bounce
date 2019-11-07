package com.findmycar.bounce.exception;

import org.springframework.http.HttpStatus;

public class MaxTransmittersForAccountExceeded extends APIException {
    public MaxTransmittersForAccountExceeded(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
