package com.bestradhasaree.product_service.service;

import com.bestradhasaree.product_service.models.Product;
import com.bestradhasaree.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl  implements  ProductService{
    private final ProductRepository productRepository;
@Autowired
    public  ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product updated, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new RuntimeException("product not found"));
        product.setName(updated.getName());
        product.setCategory(updated.getCategory());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());
        product.setStock(updated.getStock());
        product.setImageUrl(updated.getImageUrl());
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found with id "+id));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> getAllProduct() {
              return productRepository.findAll();
    }

    @Override
    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
