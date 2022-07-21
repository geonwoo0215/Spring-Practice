package com.Woo.ApiException.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle]",e);
        Map<String, String> error = new HashMap<>();
        error.put("illegal", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Map<String, String> runtimeExHandle(RuntimeException e){
        log.error("[exceptionHandle]",e);
        Map<String, String> error = new HashMap<>();
        error.put("runtime", e.getMessage());
        return error;
    }

}