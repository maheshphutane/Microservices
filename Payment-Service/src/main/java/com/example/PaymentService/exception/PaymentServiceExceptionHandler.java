package com.example.PaymentService.exception;

import com.example.PaymentService.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PaymentServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PaymentServiceException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(PaymentServiceException paymentServiceException){
        return new ResponseEntity<>(new ExceptionResponse().builder()
                .msg(paymentServiceException.getMsg())
                .errorCode(paymentServiceException.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
