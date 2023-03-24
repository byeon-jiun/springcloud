package com.example.orderservice.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
@Slf4j
public class OrderServiceController {

    private final Environment environment;
    @GetMapping("/welcome")
    public Mono<String> welcome(@RequestHeader("order-request") String header) {
        log.info("OrderService Start:::::");
        log.info("Header -> {}", header);
        log.info("OrderService End:::::");
        return Mono.just("This is OrderService:::::");
    }

    @GetMapping("/check")
    public Mono<String> check() {
        return Mono.just(String.format("This is OrderService on PORT %s", environment.getProperty("local.server.port")));
    }
}
