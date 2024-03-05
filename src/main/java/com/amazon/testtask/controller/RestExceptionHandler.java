package com.amazon.testtask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handling app exceptions
     *
     * @param ex RuntimeException
     * @return ResponseEntity
     */
    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleException(RuntimeException ex) {
        log.error("handling exception : ", ex.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
