package com.findmycar.bounce.controller;

import com.findmycar.bounce.domain.response.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrors> handleConstraintViolation(ConstraintViolationException exception) {
        return new ResponseEntity<>(
                new ValidationErrors(exception.getConstraintViolations()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
