package com.dev.ecommerce.DTOs.responses;

import com.dev.ecommerce.entities.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponse {

    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

}
