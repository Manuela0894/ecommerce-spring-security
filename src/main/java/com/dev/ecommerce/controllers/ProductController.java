package com.dev.ecommerce.controllers;

import com.dev.ecommerce.DTOs.requests.ProductRequest;
import com.dev.ecommerce.DTOs.responses.ProductResponse;
import com.dev.ecommerce.entities.Product;
import com.dev.ecommerce.services.PhotoService;
import com.dev.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
public class ProductController {

    private final ProductService produtoService;
    private final PhotoService photoService;

    public ProductController(ProductService produtoService, PhotoService photoService) {
        this.produtoService = produtoService;
        this.photoService = photoService;
    }

    @PostMapping(value = "cadastro", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveProduct(@Valid @ModelAttribute ProductRequest product) throws IOException {
        String pathPhoto = photoService.savePhoto(product.getImgUrl());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.saveProduct(product, pathPhoto));
    }

    @GetMapping(value = "view")
    public List<ProductResponse> showAll() {
        return produtoService.mostrar();
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> searchById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @PathVariable UUID id, @RequestBody Product novoProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizar(id, novoProduto));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.deleteProduto(id));
    }
}
