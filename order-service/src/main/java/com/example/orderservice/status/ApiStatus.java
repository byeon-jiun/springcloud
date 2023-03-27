package com.example.orderservice.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiStatus {

    CREATE("생성 되었습니다."),
    FETCH("조회 되었습니다.");
    private String message;

    public String getMessage() {
        return this.message;
    }
}
