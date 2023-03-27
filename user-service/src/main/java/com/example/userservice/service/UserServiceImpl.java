package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.payload.request.RequestUser;
import com.example.userservice.payload.response.ResponseUser;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.status.ApiStatus;
import com.example.userservice.utill.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public CommonResponse createUser(RequestUser requestUser) {
        User user = User.builder()
                        .email(requestUser.getEmail())
                        .name(requestUser.getName())
                        .userId(requestUser.getUserId())
                        .encryptedPwd(passwordEncoder.encode(requestUser.getPwd()))
                        .build();

        userRepository.save(user);

        ResponseUser responseUser = ResponseUser.builder()
                                                .email(user.getEmail())
                                                .name(user.getName())
                                                .userId(user.getUserId())
                                                .build();
        return CommonResponse.builder()
                            .message(ApiStatus.CREATE.getMessage())
                            .data(responseUser)
                            .status(HttpStatus.CREATED.getReasonPhrase())
                            .build();
    }

    @Override
    public CommonResponse getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(EntityNotFoundException::new);
        ResponseUser responseUser = ResponseUser.builder()
                                                .email(user.getEmail())
                                                .userId(user.getUserId())
                                                .name(user.getName())
                                                .build();
        return CommonResponse.builder()
                             .message(ApiStatus.FETCH.getMessage())
                             .data(responseUser)
                             .status(HttpStatus.OK.getReasonPhrase())
                             .build();
    }

    @Override
    public CommonResponse getUserByAll() {
        List<ResponseUser> responseUsers = userRepository.findAll().stream()
                                                                   .map(m -> {
                                                                                ResponseUser responseUser = ResponseUser.builder()
                                                                                                                        .email(m.getEmail())
                                                                                                                        .userId(m.getUserId())
                                                                                                                        .name(m.getName())
                                                                                                                        .build();
                                                                                return responseUser;
                                                                             }
                                                                   )
                                                                   .collect(Collectors.toList());
        return CommonResponse.builder()
                             .message(ApiStatus.FETCH.getMessage())
                             .data(responseUsers)
                             .status(HttpStatus.OK.getReasonPhrase())
                             .build();
    }
}
