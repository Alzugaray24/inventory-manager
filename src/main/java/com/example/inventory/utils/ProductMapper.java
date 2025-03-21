package com.example.inventory.utils;

import com.example.inventory.dtos.ProductRequestDTO;
import com.example.inventory.dtos.ProductResponseDTO;
import com.example.inventory.models.Category;
import com.example.inventory.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequestDTO productRequestDTO, Category category){

        Product product = new Product();
        product.setCategory(category);
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());

        return product;
    }


    public ProductResponseDTO toResponse (Product product){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setCreatedAt(product.getCreatedAt());
        productResponseDTO.setCategoryName(product.getCategory().getName());

        return productResponseDTO;
    }
}
