package com.example.orderService.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceException extends RuntimeException{
    private String errorCode;
    private int status;
    public OrderServiceException(String msg,String errorCode,int status){
        super(msg);
        this.errorCode = errorCode;
        this.status = status;
    }
}
