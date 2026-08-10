package com.example.product.controller;


import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest) {

        return productService.create(productRequest);
    }

    @GetMapping("/{id}")
    public Mono<ProductResponse> getById(@PathVariable Long id) {

        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public Mono<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {

        return productService.update(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {

        return productService.delete(id);
    }

}
