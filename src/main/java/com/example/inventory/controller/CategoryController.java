package com.example.inventory.controller;

import com.example.inventory.dtos.CategoryRequestDTO;
import com.example.inventory.dtos.CategoryResponseDTO;
import com.example.inventory.models.Category;
import com.example.inventory.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO responseDTO = categoryService.addCategory(categoryRequestDTO);
        return  new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        try{
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrio un problema en el servidor");
        }
    }



}
