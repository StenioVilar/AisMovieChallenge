package com.desafio.ais.api.handler;

import com.desafio.ais.api.error.ResourceNotFoundDetails;
import com.desafio.ais.api.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String NOT_FOUND_STATUS_CODE_THE_MOVIE_DB = "34";
    private static final String NOT_FOUND_STATUS_MESSAGE_THE_MOVIE_DB = "The resource you requested could not be found.";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .status_code(NOT_FOUND_STATUS_CODE_THE_MOVIE_DB)
                .status_message(NOT_FOUND_STATUS_MESSAGE_THE_MOVIE_DB)
                .detail_parameter(rfnException.getMessage())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }
}
