package com.example.product.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @Positive(message = "Price should be greater than 0")
    private Double price;

    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;

}
