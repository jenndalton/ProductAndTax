package com.gatest.products.repository;

import com.gatest.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, String> {
    Optional<Product> findProductByProductId(String productId);
}
