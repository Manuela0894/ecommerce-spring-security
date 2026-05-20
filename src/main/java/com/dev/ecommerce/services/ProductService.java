package com.dev.ecommerce.services;

import com.dev.ecommerce.DTOs.requests.ProductRequest;
import com.dev.ecommerce.DTOs.responses.ProductResponse;
import com.dev.ecommerce.entities.Product;
import com.dev.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository produtoRepository;

    public String saveProduct(@Valid ProductRequest produtoRequestDTO, String pathPhoto) {
        Product produto = new Product(produtoRequestDTO.getNome(), produtoRequestDTO.getDescricao(), produtoRequestDTO.getPreco(), pathPhoto    );
        produtoRepository.save(produto);
        return "Produto criado com sucesso.";
    }

    public String buscaPorId(UUID id) {
        Optional<Product> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            ProductResponse dto = new ProductResponse(produto.get());
            return dto.toString();
        } else {
            return "Esse ID não é válido.";
        }
    }

    public List<ProductResponse> mostrar() {
        List<Product> produtos = produtoRepository.findAll();
        List<ProductResponse> listaDeProdutos = produtos.stream().map(ProductResponse::new).toList();
        return listaDeProdutos;
    }

    public String atualizar(UUID id, Product novoProduto) {
        Optional<Product> ProdutoExistente = produtoRepository.findById(id);

        if (ProdutoExistente.isPresent()) {
            Product Produto = ProdutoExistente.get();
            Produto.setPrice(novoProduto.getPrice());
            produtoRepository.save(Produto);
            return "O preço foi modificado para " + Produto.getPrice() + ".";

        } else {
            return "Não foi achado o usuário.";
        }
    }

    public String deleteProduto(UUID id) {
        Optional<Product> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.deleteById(id);
            return "Produto deletado com sucesso.";
        } else {
            return "ID inválido.";
        }
    }
}
