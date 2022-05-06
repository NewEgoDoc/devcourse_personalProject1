package com.coffee.house.service;


import com.coffee.house.domain.Category;
import com.coffee.house.domain.Product;
import com.coffee.house.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String productName, Category category, long price) {
        return productRepository.insert(new Product(UUID.randomUUID(),productName,category,price,"",LocalDateTime.now(),LocalDateTime.now()));
    }

    @Override
    public Product createProduct(String productName, Category category, long price, String description) {
        Product product = new Product(UUID.randomUUID(), productName, category, price, description, LocalDateTime.now(), LocalDateTime.now());
        return productRepository.insert(product);
    }

}
