package com.example.PaymentService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    private long orderId;
    private long amount;
    private PaymentMode paymentMode;

}
