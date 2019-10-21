package com.findmycar.bounce.entity.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.stream.Collectors;

public class ValidationErrors {
    @Getter
    private Map<String, List<ConstraintViolationError>> errors;

    public ValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        errors = mapConstraintViolationsToErrors(constraintViolations);
    }

    private Map<String, List<ConstraintViolationError>> mapConstraintViolationsToErrors(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map(violation -> new ConstraintViolationError(
                        violation.getPropertyPath().toString(),
                        violation.getInvalidValue(),
                        violation.getPropertyPath().toString() + " " + violation.getMessage())
                ).collect(Collectors.groupingBy(ConstraintViolationError::getProperty));
    }
}
