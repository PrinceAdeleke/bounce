package com.findmycar.bounce.domain.response;

import lombok.Getter;
import lombok.Setter;

public class ViolationError {

    @Getter @Setter
    private Object rejectedValue;

    @Getter @Setter
    private String message;

    public ViolationError(Object rejectedValue, String message) {
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
