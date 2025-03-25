package com.example.inventory.controller;

import com.example.inventory.dtos.ProductRequestDTO;
import com.example.inventory.dtos.ProductResponseDTO;
import com.example.inventory.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO response = productService.addProduct(productRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();

        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrio un error inesperado");
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO){
        try{
            ProductResponseDTO responseDTO = productService.updateProduct(productRequestDTO, id);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrio un error inesperado");
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getProducts(){
        List<ProductResponseDTO> productsResponse = productService.getProducts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(productsResponse);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        try {
            ProductResponseDTO responseDTO = productService.getProductById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseDTO);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrio un problema en el servidor");
        }
    }

    @GetMapping("/products/name/{word}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByName(@PathVariable String word){
        List<ProductResponseDTO> productResponseDTOList = productService.getProductsByName(word);

        return ResponseEntity.status(HttpStatus.OK)
                .body(productResponseDTOList);
    }

    @GetMapping("/products/category/{categoryName}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable String categoryName){
        List<ProductResponseDTO> productResponseDTOList = productService.productsByCategory(categoryName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productResponseDTOList);
    }


    @GetMapping("/products/price")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByPrice(){
        List<ProductResponseDTO> productResponseDTOList = productService.productsByPrice();
        return ResponseEntity.status(HttpStatus.OK)
                .body(productResponseDTOList);
    }
}