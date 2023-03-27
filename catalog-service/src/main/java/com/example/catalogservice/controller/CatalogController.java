package com.example.catalogservice.controller;

import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.utill.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {

    private final Environment environment;
    private final CatalogService catalogService;


    @GetMapping("/check")
    public Mono<String> check() {
        return Mono.just(String.format("This is CatalogService on PORT %s", environment.getProperty("local.server.port")));
    }

    @GetMapping("/catalogs")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getCatalogs() {
        return catalogService.getAllCatalogs();
    }
}
