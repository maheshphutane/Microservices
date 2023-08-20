package com.example.orderService.service;

import com.example.orderService.dto.OrderRequestDTO;
import com.example.orderService.dto.OrderResponseDTO;
import com.example.orderService.external.client.PaymentService;
import com.example.orderService.external.client.ProductService;
import com.example.orderService.external.model.ProductResponse;
import com.example.orderService.model.Order;
import com.example.orderService.model.PaymentMode;
import com.example.orderService.model.PaymentRequest;
import com.example.orderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ProductService productService;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public long createOrder(OrderRequestDTO orderRequestDTO) {

        productService.reduceQuantity(orderRequestDTO.getProductId(),orderRequestDTO.getQuantity());
        log.info("Blocking the quantity of the product "+orderRequestDTO.getProductId());

        Order order = Order.builder()
                .productId(orderRequestDTO.getProductId())
                .quantity(orderRequestDTO.getQuantity())
                .orderDate(Instant.now())
                .orderStatus("CREATED")
                .amount(orderRequestDTO.getAmount())
                .build();

        Order order2 = orderRepository.save(order);

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(order2.getAmount())
                .orderId(order2.getId())
                .paymentMode(PaymentMode.UPI)
                .build();

        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Done the payment for the order "+order.getId());
            orderStatus = "SUCCESSFUL";
        }catch (Exception e){
            log.info("Payment failed for the order "+order.getId());
            orderStatus = "FAILED";
        }

        order2.setOrderStatus(orderStatus);
        orderRepository.save(order2);
        log.info("Order Placed successfully");

        return order2.getId();
    }

    @Override
    public OrderResponseDTO getOrderById(long id) {

        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) return null;

        ProductResponse productResponse = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/"+order.get().getProductId(), ProductResponse.class);
        log.info(productResponse);
        OrderResponseDTO.ProductDetails productDetails = OrderResponseDTO.ProductDetails.builder()
                .id(productResponse.getId())
                .p_name(productResponse.getP_name())
                .cost(productResponse.getCost())
                .description(productResponse.getDescription())
                .quantity(productResponse.getQuantity())
                .build();

        OrderResponseDTO orderResponseDTO = OrderResponseDTO.builder()
                .orderId(order.get().getId())
                .orderDate(order.get().getOrderDate())
                .orderStatus(order.get().getOrderStatus())
                .amount(order.get().getAmount())
                .quantity(order.get().getQuantity())
                .productDetails(productDetails)
                .build();

        return orderResponseDTO;
    }
}
