package com.bestradhasaree.product_service.repository;

import com.bestradhasaree.product_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {
    List<Product> findByCategory(String category);

}
