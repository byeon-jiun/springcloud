package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.payload.request.RequestUser;
import com.example.userservice.utill.CommonResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    CommonResponse createUser(RequestUser user);

    CommonResponse getUserByUserId(String userId);

    CommonResponse getUserByAll();

    User getUserDetailsByEmail(String userName);
}
