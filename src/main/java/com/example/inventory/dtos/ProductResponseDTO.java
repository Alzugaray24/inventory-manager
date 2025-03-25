package com.example.inventory.dtos;

//import com.example.inventory.models.Category;
import com.example.inventory.models.Category;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private LocalDateTime createdAt;
    private String categoryName;

    public ProductResponseDTO(Long id, String name, String description, Double price, LocalDateTime createdAt, String categoryName){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
        this.categoryName = categoryName;
    }

    public ProductResponseDTO(){}


    public ProductResponseDTO(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
