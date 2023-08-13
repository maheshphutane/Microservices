package com.example.productService.exception;

import com.example.productService.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ExceptionResponse> controllerExceptionHandler(ProductServiceException productServiceException){
        return new ResponseEntity<>(new ExceptionResponse().builder()
                .errorCode(productServiceException.getErrorCode())
                .msg(productServiceException.getMessage()).build(),HttpStatus.NOT_FOUND);
    }
}
