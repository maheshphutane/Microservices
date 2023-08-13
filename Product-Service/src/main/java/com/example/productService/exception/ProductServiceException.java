package com.example.productService.exception;

import lombok.Data;

@Data
public class ProductServiceException extends RuntimeException{
    private String errorCode;

    public ProductServiceException(String msg,String errorCode){
        super(msg);
        this.errorCode = errorCode;
    }
}
