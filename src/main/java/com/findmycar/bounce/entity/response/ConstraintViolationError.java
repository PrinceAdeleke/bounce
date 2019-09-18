package com.findmycar.bounce.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

public class ConstraintViolationError {
    @Setter @Getter @JsonIgnore
    private String property;

    @Getter @Setter
    private Object rejectedValue;

    @Getter @Setter
    private String message;

    public ConstraintViolationError(String property, Object rejectedValue, String message) {
        this.property = property;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
