package com.example.PaymentService.service;

import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.model.TransactionDetails;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    TransactionDetails getPaymentDetailsById(Long id);
}
