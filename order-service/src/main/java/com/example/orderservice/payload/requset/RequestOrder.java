package com.example.orderservice.payload.requset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrder {
    private String productId;
    private Integer qry;
    private Integer unitPrice;

    @Builder.Default
    private String orderId = UUID.randomUUID().toString();

    @JsonIgnore
    private String userId;
}
