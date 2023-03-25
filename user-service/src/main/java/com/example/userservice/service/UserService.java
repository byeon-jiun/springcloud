package com.example.userservice.service;

import com.example.userservice.payload.RequestUser;
import com.example.userservice.utill.CommonResponse;

public interface UserService {
    CommonResponse createUser(RequestUser user);
}
