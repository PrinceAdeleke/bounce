package com.findmycar.bounce.domain.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.stream.Collectors;

public class ValidationErrors {

    @Getter @Setter
    private Map<String, List<ViolationError>> errors;

    public ValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        errors = new HashMap<>();
        processErrors(constraintViolations);
    }

    private void processErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations
                .stream()
                .collect(Collectors.groupingBy(ConstraintViolation::getPropertyPath))
                .forEach((key, value) -> errors.put(
                        key.toString(),
                        value.stream()
                                .map(error -> new ViolationError(
                                        error.getInvalidValue(),
                                        key + " " + error.getMessage()))
                                .collect(Collectors.toList())
                ));
    }
}
