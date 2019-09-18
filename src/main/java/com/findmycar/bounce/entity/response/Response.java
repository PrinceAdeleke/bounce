package com.findmycar.bounce.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(Include.NON_NULL)
public class Response {
    private boolean success;
    private String message;

    @JsonProperty("data")
    private Object body;

    public Response(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getBody() {
        return body;
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }
}
