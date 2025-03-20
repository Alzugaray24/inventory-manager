package com.example.inventory.services;

import com.example.inventory.dtos.ProductRequestDTO;
import com.example.inventory.dtos.ProductResponseDTO;
import com.example.inventory.models.Category;
import com.example.inventory.models.Product;
import com.example.inventory.repositories.CategoryRepository;
import com.example.inventory.repositories.ProductRepository;
import com.example.inventory.utils.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    private final ProductMapper mapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductMapper mapper, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

//    private ProductMapper mapper;


    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO){

//        ProductMapper mapper = new ProductMapper();

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(()-> new EntityNotFoundException("La categoria no existe"));

        Product newProduct = mapper.toEntity(productRequestDTO, category);

        productRepository.saveAndFlush(newProduct);

        return mapper.toResponse(newProduct);
    }

    public void deleteProduct(Long id){
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("El producto no existe"));

        productRepository.delete(productToDelete);
    }
}
