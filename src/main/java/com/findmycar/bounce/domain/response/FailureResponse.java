package com.findmycar.bounce.domain.response;

public class FailureResponse extends APIResponse {
    public FailureResponse(String message, Object data) {
        super(false, message, data);
    }
}
