package com.example.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
@Slf4j
public class UserServiceController {
    private final Environment environment;
    @GetMapping("/welcome")
    public Mono<String> welcome(@RequestHeader("user-request") String header) {
        log.info("UserService Start:::::");
        log.info("Header -> {}",header);
        log.info("UserService End:::::");
        return Mono.just("This is UserService:::::");
    }

    @GetMapping("/check")
    public Mono<String> check() {
        return Mono.just(String.format("This is UserService on PORT %s", environment.getProperty("local.server.port")));
    }
}
