package com.dev.ecommerce.DTOs.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private String description;
    private Double price;
    private MultipartFile imgUrl;

    public ProductRequest(String name, String description, Double price, MultipartFile imgUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
