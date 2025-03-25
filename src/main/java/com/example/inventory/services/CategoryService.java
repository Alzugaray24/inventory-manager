package com.example.inventory.services;

import com.example.inventory.dtos.CategoryRequestDTO;
import com.example.inventory.dtos.CategoryResponseDTO;
import com.example.inventory.dtos.ProductResponseDTO;
import com.example.inventory.models.Category;
import com.example.inventory.models.Product;
import com.example.inventory.repositories.CategoryRepository;
import com.example.inventory.utils.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper mapper){
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }


    public CategoryResponseDTO addCategory (CategoryRequestDTO categoryRequestDTO){
        Category newCategory = mapper.toEntity(categoryRequestDTO);
        categoryRepository.saveAndFlush(newCategory);
        return mapper.toResponse(newCategory);
    }


    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("La categoria no existe"));

        categoryRepository.delete(category);
    }

    public CategoryResponseDTO updateCategory(CategoryRequestDTO requestDTO, Long id){
        Category categoryFound = categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("La categoria no existe"));

        categoryFound.setName(requestDTO.getName());

        categoryRepository.save(categoryFound);

        return mapper.toResponse(categoryFound);
    }


    public List<CategoryResponseDTO> getCategories(){
        List<Category> category = categoryRepository.findAll();

        return category.stream()
                .map(c -> new CategoryResponseDTO(
                        c.getId(),
                        c.getName(),
                        c.getProducts().stream()
                                .map(Product::getName)
                                .collect(Collectors.toList())
                )).collect(Collectors.toList());
    }

    public List<ProductResponseDTO> getProductsFromCategory(Long id){
        Category categoryFound = categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("La categoria no existe"));

        return categoryFound.getProducts().stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCreatedAt(),
                        product.getCategory().getName()
                )).collect(Collectors.toList());
    }


}
