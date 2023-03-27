package com.example.orderservice.service;

import com.example.orderservice.payload.requset.RequestOrder;
import com.example.orderservice.utill.CommonResponse;

public interface OrderService {
    CommonResponse createOrder(RequestOrder requestOrder);

    CommonResponse getOrderByOrderId(String orderId);

    CommonResponse getOrderByUserId(String userId);
}
