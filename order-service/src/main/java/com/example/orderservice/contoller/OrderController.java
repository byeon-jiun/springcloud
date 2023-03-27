package com.example.orderservice.contoller;

import com.example.orderservice.payload.requset.RequestOrder;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.utill.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final Environment environment;
    private final OrderService orderService;

    @GetMapping("/check")
    public Mono<String> check() {
        return Mono.just(String.format("This is OrderService on PORT %s", environment.getProperty("local.server.port")));
    }

    @PostMapping("/{userId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder requestOrder) {
        requestOrder.setUserId(userId);
        return orderService.createOrder(requestOrder);
    }

    @GetMapping("/{userId}/orders/")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getOrders(@PathVariable("userId") String userId) {
        return orderService.getOrderByUserId(userId);
    }

    @GetMapping("/{orderId}/orders/")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getOrder(@PathVariable("orderId") String orderId) {
        return orderService.getOrderByOrderId(orderId);
    }
}
