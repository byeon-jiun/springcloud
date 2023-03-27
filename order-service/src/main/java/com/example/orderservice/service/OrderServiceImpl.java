package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.payload.requset.RequestOrder;
import com.example.orderservice.payload.response.ResponseOrder;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.status.ApiStatus;
import com.example.orderservice.utill.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public CommonResponse createOrder(RequestOrder requestOrder) {
        Order order = Order.builder()
                           .productId(requestOrder.getProductId())
                           .qty(requestOrder.getQry())
                           .unitPrice(requestOrder.getUnitPrice())
                           .totalPrice(requestOrder.getQry() * requestOrder.getUnitPrice())
                           .orderId(requestOrder.getOrderId())
                           .userId(requestOrder.getUserId())
                           .build();
        orderRepository.save(order);

        ResponseOrder responseOrder = ResponseOrder.builder()
                                                   .productId(order.getProductId())
                                                   .orderId(order.getOrderId())
                                                   .qry(order.getQty())
                                                   .unitPrice(order.getUnitPrice())
                                                   .totalPrice(order.getTotalPrice())
                                                   .createdAt(order.getCreatedAt())
                                                   .build();
        return CommonResponse.builder()
                             .message(ApiStatus.CREATE.getMessage())
                             .data(responseOrder)
                             .status(HttpStatus.CREATED.getReasonPhrase())
                             .build();
    }

    @Override
    public CommonResponse getOrderByOrderId(String orderId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);
        ResponseOrder responseOrder = ResponseOrder.builder()
                                                   .productId(order.getProductId())
                                                   .orderId(order.getOrderId())
                                                   .qry(order.getQty())
                                                   .unitPrice(order.getUnitPrice())
                                                   .totalPrice(order.getTotalPrice())
                                                   .createdAt(order.getCreatedAt())
                                                   .build();

        return CommonResponse.builder()
                .message(ApiStatus.FETCH.getMessage())
                .data(responseOrder)
                .status(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @Override
    public CommonResponse getOrderByUserId(String userId) {
        List<ResponseOrder> responseOrders = orderRepository.findByUserId(userId).stream()
                                                                                 .map(m -> {
                                                                                     ResponseOrder responseOrder = ResponseOrder.builder()
                                                                                                                                .productId(m.getProductId())
                                                                                                                                .orderId(m.getOrderId())
                                                                                                                                .qry(m.getQty())
                                                                                                                                .unitPrice(m.getUnitPrice())
                                                                                                                                .totalPrice(m.getTotalPrice())
                                                                                                                                .createdAt(m.getCreatedAt())
                                                                                                                                .build();
                                                                                     return responseOrder;
                                                                                 })
                                                                                 .collect(Collectors.toList());
        return CommonResponse.builder()
                             .message(ApiStatus.FETCH.getMessage())
                             .data(responseOrders)
                             .status(HttpStatus.OK.getReasonPhrase())
                             .build();
    }
}
