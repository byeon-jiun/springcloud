package com.example.catalogservice.utill;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {
    private String message;
    private T data;
    private String status;
}
