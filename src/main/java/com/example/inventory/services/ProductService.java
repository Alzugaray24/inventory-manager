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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO){

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


    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, Long id){

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(()-> new EntityNotFoundException("La categoria no existe"));

        Product productToUpdate = productRepository.findById(id)
                .map(product -> {
                    product.setName(productRequestDTO.getName());
                    product.setPrice(productRequestDTO.getPrice());
                    product.setDescription(productRequestDTO.getDescription());
                    product.setCategory(category);
                    return productRepository.saveAndFlush(product);
                }).orElseThrow(()-> new EntityNotFoundException("El producto no existe"));

        return mapper.toResponse(productToUpdate);
    }

    public List<ProductResponseDTO> getProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(p -> new ProductResponseDTO(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getCreatedAt(),
                p.getCategory().getName()
        )).collect(Collectors.toList());
    }


    public ProductResponseDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("El producto no existe"));

        return mapper.toResponse(product);
    }

    public List<ProductResponseDTO> getProductsByName(String name) {

        List<Product> products = productRepository.findByNameStartingWith(name);

        return products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCreatedAt(),
                        product.getCategory().getName()))
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> productsByCategory(String categoryName){

        return productRepository.findByCategory(categoryName).stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCreatedAt(),
                        product.getCategory().getName()
                )).toList();
    }

    public List<ProductResponseDTO> productsByPrice(){
        return productRepository.findByPrice().stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCreatedAt(),
                        product.getCategory().getName()
                )).toList();
    }
}
