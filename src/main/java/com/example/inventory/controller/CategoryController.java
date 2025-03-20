package com.example.inventory.controller;

import com.example.inventory.dtos.CategoryRequestDTO;
import com.example.inventory.dtos.CategoryResponseDTO;
import com.example.inventory.models.Category;
import com.example.inventory.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;


    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO responseDTO = service.addCategory(categoryRequestDTO);
        return  new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
