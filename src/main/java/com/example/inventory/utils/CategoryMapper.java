package com.example.inventory.utils;

import com.example.inventory.dtos.CategoryRequestDTO;
import com.example.inventory.dtos.CategoryResponseDTO;
import com.example.inventory.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDTO categoryRequestDTO){
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());

        return category;
    }

    public CategoryResponseDTO toResponse(Category category){
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        return categoryResponseDTO;
    }
}
