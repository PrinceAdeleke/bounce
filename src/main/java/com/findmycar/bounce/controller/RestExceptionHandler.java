package com.findmycar.bounce.controller;

import com.findmycar.bounce.entity.response.APIResponse;
import com.findmycar.bounce.entity.response.ValidationErrors;
import com.findmycar.bounce.exception.APIException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ValidationErrors handleConstraintViolation(ConstraintViolationException exception) {
        return new ValidationErrors(exception.getConstraintViolations());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public APIResponse handleEntityNotFoundException(EntityNotFoundException exception) {
        return new APIResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public APIResponse handleAPIException(APIException exception) {
        return new APIResponse(null, exception.getMessage(), exception.getStatus());
    }
}
