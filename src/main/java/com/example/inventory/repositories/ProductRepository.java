package com.example.inventory.repositories;


import com.example.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product p WHERE LOWER(p.name) LIKE LOWER(CONCAT(:name, '%'))", nativeQuery = true)
    List<Product> findByNameStartingWith(@Param("name") String name);

    @Query(value = "SELECT p.* FROM product p " +
            "JOIN category c ON p.category_id = c.id " +
            "WHERE c.name LIKE CONCAT('%', :categoryName, '%')",
            nativeQuery = true)
    List<Product> findByCategory(@Param("categoryName") String categoryName);

    @Query(value = "SELECT * FROM product p ORDER BY p.price DESC", nativeQuery = true)
    List<Product> findByPrice();


}
