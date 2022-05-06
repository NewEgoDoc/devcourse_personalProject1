package com.coffee.house.repository;

import com.coffee.house.domain.Category;
import com.coffee.house.domain.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductJdbcRepositoryTest {

    @Autowired
    ProductRepository repository;

    private final Product newProduct
            = new Product(UUID.randomUUID(),
            "productName",
            Category.COFFEE_BEAN_PACKAGE,
            50000L,
            "",
            LocalDateTime.now(),LocalDateTime.now());

    @Test
    @Order(1)
    public void 상품을_추가할_수_있다(){
        repository.insert(newProduct);
        List<Product> all = repository.findAll();
        assertThat(all.isEmpty(), is(false));
    }

    @Test
    @Order(2)
    public void 상품을_이름으로_조회할수_있다(){
        Optional<Product> product = repository.findByName(newProduct.getProductName());
        assertThat(product.isEmpty(),is(false));
    }

    @Test
    @Order(3)
    public void 상품을_아이디로_조회할수_있다(){
        Optional<Product> product = repository.findById(newProduct.getProductId());
        assertThat(product.isEmpty(),is(false));
    }

    @Test
    @Order(4)
    public void 상품을_카테고리로_조회할수_있다(){
        List<Product> products = repository.findByCategory(newProduct.getCategory());
        assertThat(products.isEmpty(),is(false));
    }

    @Test
    @Order(5)
    public void 상품을_수정_할_수_있다(){
        newProduct.changeProductName("updatedProductName");
        repository.update(newProduct);

        Optional<Product> product = repository.findById(newProduct.getProductId());
        assertThat(product.isEmpty(), is(false));
        assertThat(product.get(),samePropertyValuesAs(newProduct));
    }

    @Test
    @Order(6)
    public void 상품을_전체를_삭제한다(){
        repository.deleteAll();
        List<Product> all = repository.findAll();
        assertThat(all.isEmpty(),is(true));
    }
}