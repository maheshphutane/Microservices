package com.example.orderService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private long quantity;
    private Instant orderDate;
    private String orderStatus;
    private long amount;

    private ProductDetails productDetails;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetails{
        private long id;
        private String p_name;
        private String description;
        private long cost;
        private long quantity;
    }
}
