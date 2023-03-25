package com.example.userservice.utill;

import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Timer;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class CommonResponse<T> {
    private String message;
    private T data;
    private String status;
}
