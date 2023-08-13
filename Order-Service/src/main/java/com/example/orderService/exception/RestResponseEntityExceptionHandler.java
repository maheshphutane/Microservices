package com.example.orderService.exception;

import com.example.orderService.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<ExceptionResponse> controllerExceptionHandler(OrderServiceException orderServiceException){
        return new ResponseEntity<>(new ExceptionResponse().builder()
                .errorCode(orderServiceException.getErrorCode())
                .msg(orderServiceException.getMessage()).build(),
                HttpStatusCode.valueOf(orderServiceException.getStatus()));
    }
}
