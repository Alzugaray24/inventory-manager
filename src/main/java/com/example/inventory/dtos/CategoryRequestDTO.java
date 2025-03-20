package com.example.inventory.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @NotNull(message = "La categoria no puede ser null")
    private String name;
}
