package com.excel.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoResourcesFoundException(NoResourceFoundException ex) {
        log.error("Not found error: ", ex);

        ApiError body = new ApiError("NOT_FOUND_ERROR", "No resource not found error.", ex.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
