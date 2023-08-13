package com.example.orderService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private long productId;
    private long quantity;
    private String orderStatus;
    private long amount;
}
