package com.example.orderService.service;

import com.example.orderService.dto.OrderRequestDTO;
import com.example.orderService.external.client.ProductService;
import com.example.orderService.model.Order;
import com.example.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Override
    public long createOrder(OrderRequestDTO orderRequestDTO) {
        productService.reduceQuantity(orderRequestDTO.getProductId(),orderRequestDTO.getQuantity());
        Order order = Order.builder()
                .productId(orderRequestDTO.getProductId())
                .quantity(orderRequestDTO.getQuantity())
                .orderDate(Instant.now())
                .orderStatus(orderRequestDTO.getOrderStatus())
                .amount(orderRequestDTO.getAmount())
                .build();
        Order order2 = orderRepository.save(order);
        return order2.getId();
    }

    @Override
    public Order getOrderById(long id) {

        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) return null;
        return order.get();
    }
}
