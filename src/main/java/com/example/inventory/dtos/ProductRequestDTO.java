package com.example.inventory.dtos;

import com.example.inventory.models.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @NotNull(message = "El nombre del producto no puede ser nulo.")
    @Size(min = 2, max = 100, message = "El nombre del producto debe tener entre 2 y 100 caracteres.")
    private String name;

    private String description;

    @NotNull(message = "El precio del producto no puede ser nulo")
    @Min(value = 0, message = "El precio del producto debe ser mayor o igual a 0.")
    private Double price;

    @NotNull(message = "La categoria no puede ser null")
    private Long categoryId;


}
