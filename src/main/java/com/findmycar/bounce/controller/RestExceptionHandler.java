package com.findmycar.bounce.controller;

import com.findmycar.bounce.domain.response.FailureResponse;
import com.findmycar.bounce.domain.response.ValidationErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<FailureResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(
                new FailureResponse(exception.getMessage(), null, HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new FailureResponse(exception.getMessage(), null, status), status);
    }
}
