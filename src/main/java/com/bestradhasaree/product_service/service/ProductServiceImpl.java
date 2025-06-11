package com.bestradhasaree.product_service.service;

import com.bestradhasaree.product_service.exception.ProductAlreadyExistsException;
import com.bestradhasaree.product_service.exception.ProductNotFoundException;
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
        // ✅ Check for duplicate by name (you can use any unique field)
        Optional<Product> existing = productRepository.findByName(product.getName());
        if (existing.isPresent()) {
            throw new ProductAlreadyExistsException("Product with name '" + product.getName() + "' already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product updated, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
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
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));    }

    @Override
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Cannot delete. Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProduct() {
              return productRepository.findAll();
    }

    @Override
    public List<Product> getByCategory(String category) {
        List<Product> productList=productRepository.findByCategory(category);
     if( productList.isEmpty())
     {
         throw  new ProductNotFoundException("Product not found with category: " + category);
     }
        return  productList;
    }
}
