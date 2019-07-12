package com.findmycar.bounce.domain.response;

public class SuccessResponse extends APIResponse {
    public SuccessResponse(String message, Object data) {
        super(true, message, data);
    }
}
