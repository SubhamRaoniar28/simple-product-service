package com.example.product.service;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.entity.Product;
import com.example.product.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.product.repository.ProductRepository;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Mono<ProductResponse> create(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();

        Product savedProduct = productRepository.save(product);

        return Mono.just(entityToResponse(savedProduct));

    }

    @Override
    public Mono<ProductResponse> getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + id));

        return Mono.just(entityToResponse(product));
    }

    @Override
    public Mono<ProductResponse> update(Long id, ProductRequest productRequest) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + id));

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        Product updatedProduct = productRepository.save(product);

        return Mono.just(entityToResponse(updatedProduct));
    }

    @Override
    public Mono<Void> delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + id));

        productRepository.delete(product);

        return Mono.empty();
    }

    private ProductResponse entityToResponse(Product product) {
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
        return productResponse;
    }


}
