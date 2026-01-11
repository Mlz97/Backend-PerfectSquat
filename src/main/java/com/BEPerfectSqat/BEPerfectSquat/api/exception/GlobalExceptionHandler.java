package com.BEPerfectSqat.BEPerfectSquat.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.BEPerfectSqat.BEPerfectSquat.domain.exception.AnalysisSessionNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnalysisSessionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAnalysisSessionNotFound(AnalysisSessionNotFoundException ex){
        return ex.getMessage();
    }


}
