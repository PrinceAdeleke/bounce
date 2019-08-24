package com.findmycar.bounce.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class APIResponse {

    @Getter @Setter
    private boolean success;

    @Getter @Setter
    private String message;

    @Getter @Setter
    private Object data;

    public APIResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public APIResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public APIResponse(boolean success) {
        this.success = success;
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }
}
