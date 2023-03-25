package com.example.userservice.controller;

import com.example.userservice.payload.RequestUser;
import com.example.userservice.service.UserService;
import com.example.userservice.utill.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final Environment environment;
    public final UserService userService;

    @GetMapping("/heath_check")
    public String status() {
        return "It's Working User Service";
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
}
