package com.bestradhasaree.product_service.repository;

import com.bestradhasaree.product_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {
    List<Product> findByCategory(String category);

    Optional<Product> findByName(String name);
}
