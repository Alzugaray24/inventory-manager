package com.example.inventory.dtos;

import com.example.inventory.models.Product;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private List<String> products;

    public CategoryResponseDTO(Long id, String name, List<String> products){
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public CategoryResponseDTO(){}
}
