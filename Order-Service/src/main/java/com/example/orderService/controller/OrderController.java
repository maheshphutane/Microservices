package com.example.orderService.controller;

import com.example.orderService.dto.OrderRequestDTO;
import com.example.orderService.dto.OrderResponseDTO;
import com.example.orderService.exception.OrderServiceException;
import com.example.orderService.model.Order;
import com.example.orderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<Long> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return new ResponseEntity<>(orderService.createOrder(orderRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Order> getOrderById(@RequestParam long id){
        Order order = orderService.getOrderById(id);
        if(order==null) throw new OrderServiceException("Order with id: "+id+" is Not Found","ORDER_NOT_FOUND",404);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
