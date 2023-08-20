package com.example.PaymentService.service;

import com.example.PaymentService.exception.PaymentServiceException;
import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.model.PaymentStatus;
import com.example.PaymentService.model.TransactionDetails;
import com.example.PaymentService.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Making payment for the the orderId : "+paymentRequest.getOrderId());
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .paymentMode(paymentRequest.getPaymentMode())
                .paymentDate(Instant.now())
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .build();

        paymentRepository.save(transactionDetails);
        log.info("Payment successful with transaction id : " + transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public TransactionDetails getPaymentDetailsById(Long id) {
        log.info("Requested Transaction details for id: "+id);
        Optional<TransactionDetails> transactionDetails = paymentRepository.findById(id);
        if(transactionDetails.isEmpty()){
            throw new PaymentServiceException("Transaction with id "+id+ " is Not Found",
                    "TRANSACTION_NOT_FOUND");
        }
        log.info("Details fetched successfully");
        return transactionDetails.get();

    }
}
