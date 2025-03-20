package com.example.inventory.services;

import com.example.inventory.dtos.CategoryRequestDTO;
import com.example.inventory.dtos.CategoryResponseDTO;
import com.example.inventory.models.Category;
import com.example.inventory.repositories.CategoryRepository;
import com.example.inventory.utils.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
