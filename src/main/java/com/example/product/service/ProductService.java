package com.example.product.service;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductResponse> create(ProductRequest productRequest);

    Mono<ProductResponse> getById(Long id);

    Mono<ProductResponse> update(Long id, ProductRequest productRequest);

    Mono<Void> delete(Long id);

}
