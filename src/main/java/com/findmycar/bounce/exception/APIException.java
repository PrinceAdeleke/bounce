package com.findmycar.bounce.exception;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {

    private HttpStatus status;

    public APIException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
