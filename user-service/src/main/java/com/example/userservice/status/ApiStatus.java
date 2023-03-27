package com.example.userservice.status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public enum ApiStatus {

    CREATE("생성 되었습니다."),
    FETCH("조회 되었습니다.");
    private String message;

    public String getMessage() {
        return this.message;
    }
}
