package com.example.orderService.dto;

import java.time.Instant;

public class OrderResponseDTO {
    private Long productId;
    private long quantity;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
}
