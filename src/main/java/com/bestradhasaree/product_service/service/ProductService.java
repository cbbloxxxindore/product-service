package com.bestradhasaree.product_service.service;

import com.bestradhasaree.product_service.ProductServiceApplication;
import com.bestradhasaree.product_service.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product,Long id);
    Product getProduct(Long  id);
    void  deleteProductById(Long id);

    List<Product> getAllProduct();

    List<Product> getByCategory(String category);
}
