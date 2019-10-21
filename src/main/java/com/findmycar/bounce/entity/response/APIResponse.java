package com.findmycar.bounce.entity.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class APIResponse extends ResponseEntity<Response> {
    public APIResponse(Object body, String message, HttpStatus status) {
        super(
                new Response(
                        !status.isError(),
                        !(message == null || message.isEmpty()) ? message : status.getReasonPhrase(), body
                ), status
        );
    }

    public APIResponse(Object body, HttpStatus status) {
        super(new Response(!status.isError(), null, body), status);
    }
}
