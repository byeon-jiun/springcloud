package com.example.userservice.controller;

import com.example.userservice.payload.request.RequestUser;
import com.example.userservice.service.UserService;
import com.example.userservice.utill.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final Environment environment;
    public final UserService userService;

    @GetMapping("/check")
    public Mono<String> check() {
        return Mono.just(String.format("This is UserService"
                + ", port(local.server.port)=" + environment.getProperty("local.server.port")
                + ", port(server.port)=" + environment.getProperty("server.port")
                + ", port(token secret=)=" + environment.getProperty("token.secret")
                + ", port(token expiration time)=" + environment.getProperty("token.expiration_time"))
        );
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createUser(@RequestBody RequestUser user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getUsers() {
        return userService.getUserByAll();
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUserByUserId(userId);
    }
}
