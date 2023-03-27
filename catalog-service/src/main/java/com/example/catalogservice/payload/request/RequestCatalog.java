package com.example.catalogservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCatalog {
    private String productId;
    private Integer qry;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
