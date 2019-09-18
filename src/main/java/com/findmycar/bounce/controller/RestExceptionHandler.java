package com.findmycar.bounce.controller;

import com.findmycar.bounce.entity.response.APIResponse;
import com.findmycar.bounce.entity.response.ValidationErrors;
import com.findmycar.bounce.exception.APIException;
import com.findmycar.bounce.exception.BadRequestException;
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
    public APIResponse handleConstraintViolation(ConstraintViolationException exception) {
        return new ValidationErrors(exception.getConstraintViolations());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public APIResponse handleEntityNotFoundException(EntityNotFoundException exception) {
        return new APIResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public APIResponse handleAPIException(APIException exception) {
        return new APIResponse(exception.getMessage(), exception.getStatus());
    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return new ResponseEntity<>(new FailureResponse(exception.getMessage(), null, status), status);
//    }
}
