package com.findmycar.bounce.entity.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class APIResponse extends ResponseEntity<Response> {
    public APIResponse(Object body, HttpStatus status) {
        super(new Response(!status.isError(), status.getReasonPhrase(), body), status);
    }

    public APIResponse(String message, HttpStatus status) {
        super(new Response(!status.isError(), message, null), status);
    }

    public APIResponse(HttpStatus status) {
        super(new Response(!status.isError(), status.getReasonPhrase(), null), status);
    }
}
