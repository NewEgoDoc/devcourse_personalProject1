package com.coffee.house.service;


import com.coffee.house.domain.Category;
import com.coffee.house.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductByCategory(Category category);

    List<Product> getAllProducts();

    Product createProduct(String productName, Category category, long price);

    Product createProduct(String productName, Category category, long price, String description);
}
