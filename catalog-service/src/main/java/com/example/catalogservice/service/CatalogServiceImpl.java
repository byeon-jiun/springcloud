package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.payload.response.ResponseCatalog;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.status.ApiStatus;
import com.example.catalogservice.utill.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;
    @Override
    public CommonResponse getAllCatalogs() {
        List<ResponseCatalog> catalogs = catalogRepository.findAll().stream()
                                                                    .map(m -> {
                                                                        ResponseCatalog catalog = ResponseCatalog.builder()
                                                                                                                 .productId(m.getProductId())
                                                                                                                 .productName(m.getProductName())
                                                                                                                 .stock(m.getStock())
                                                                                                                 .unitPrice(m.getUnitPrice())
                                                                                                                 .createdAt(m.getCreatedAt())
                                                                                                                 .build();
                                                                        return catalog;
                                                                    })
                                                                    .collect(Collectors.toList());
        return CommonResponse.builder()
                             .message(ApiStatus.FETCH.getMessage())
                             .data(catalogs)
                             .status(HttpStatus.OK.getReasonPhrase())
                             .build();
    }
}
