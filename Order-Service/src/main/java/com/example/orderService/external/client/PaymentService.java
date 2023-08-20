package com.example.orderService.external.client;

import com.example.orderService.model.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {
    @PostMapping("/doPayment")
    ResponseEntity<Void> doPayment(@RequestBody PaymentRequest paymentRequest);
}
