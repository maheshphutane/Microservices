package com.example.PaymentService.exception;

import lombok.Data;

@Data
public class PaymentServiceException extends RuntimeException {
    private String msg;
    private String errorCode;
    public PaymentServiceException(String msg, String errorCode) {
        super(msg);
        this.msg = msg;
        this.errorCode = errorCode;
    }
}
