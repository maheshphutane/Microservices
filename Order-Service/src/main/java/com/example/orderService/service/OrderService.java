package com.example.orderService.service;

import com.example.orderService.dto.OrderRequestDTO;
import com.example.orderService.dto.OrderResponseDTO;
import com.example.orderService.model.Order;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    long createOrder(OrderRequestDTO order);

    OrderResponseDTO getOrderById(long id);
}
