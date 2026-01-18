package com.BEPerfectSqat.BEPerfectSquat.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.BEPerfectSqat.BEPerfectSquat.api.dto.ErrorResponse;
import com.BEPerfectSqat.BEPerfectSquat.domain.exception.AnalysisSessionNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnalysisSessionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAnalysisSessionNotFound(AnalysisSessionNotFoundException ex){
        ErrorResponse response = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "SESSION_NOT_FOUND",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex){
        ErrorResponse response = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "BAD_REQUEST",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
