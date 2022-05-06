package com.coffee.house.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void makeRequiredArgsConstructor(){
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        Product product = new Product(id, now);

        assertEquals(id.toString(), product.getProductId().toString());
        assertEquals(now.toString(), product.getCreateAt().toString());
    }

    @Test
    public void test_setPrice(){
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        Product product = new Product(id, now);

        product.setPrice(50000);

        assertEquals(50000,product.getPrice());
    }

    @Test
    public void test_changeCategory(){
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        Product product = new Product(id, now);

        product.changeCategory(Category.COFFEE_BEAN_PACKAGE);

        assertEquals(Category.COFFEE_BEAN_PACKAGE.toString(),product.getCategory().toString());
    }


    @Test
    public void test_changeProductName(){
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        Product product = new Product(id, now);

        product.changeProductName("testtest");

        assertEquals("testtest",product.getProductName());
    }

    @Test
    public void test_changeDescription(){
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        String description = "test description";
        Product product = new Product(id, now);

        product.changeDescription(description);

        assertEquals(description,product.getDescription());
    }

}