package com.example.orderService.external.decoder;

import com.example.orderService.exception.OrderServiceException;
import com.example.orderService.external.response.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("::{}",response.request().url());
        log.info("::{}",response.request().headers());

        try {
            ExceptionResponse exceptionResponse = objectMapper.readValue(response.body().asInputStream(),
                    ExceptionResponse.class);
            return new OrderServiceException(exceptionResponse.getMsg(),
                    exceptionResponse.getErrorCode(),
                    response.status());
        } catch (IOException e) {
            throw new OrderServiceException("Kidhar to problem ho gaya he bhai","Internal Server Error",500);
        }

    }
}
