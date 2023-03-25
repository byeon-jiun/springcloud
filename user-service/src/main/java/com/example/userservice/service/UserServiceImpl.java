package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.payload.RequestUser;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.utill.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public CommonResponse createUser(RequestUser requestUser) {
        requestUser.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        User user = mapper.map(requestUser, User.class);
        user.setEncryptedPwd("encrypted_password");
        userRepository.save(user);
        return CommonResponse.builder()
                            .message("created user!!")
                            .status(HttpStatus.CREATED.getReasonPhrase())
                            .build();
    }
}
