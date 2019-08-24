package com.findmycar.bounce.domain.response;

import org.springframework.http.HttpStatus;

public class FailureResponse extends APIResponse {
    private HttpStatus httpStatus;

    public FailureResponse(String message, Object data, HttpStatus httpStatus) {
        super(false, message, data);
        this.httpStatus = httpStatus;
    }

    public String getStatus() {
        return httpStatus.getReasonPhrase();
    }

    public int getError() {
        return httpStatus.value();
    }
}
