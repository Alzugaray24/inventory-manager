package com.example.inventory.dtos;

//import com.example.inventory.models.Category;
import com.example.inventory.models.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private LocalDateTime createdAt;
    private String categoryName;

}
