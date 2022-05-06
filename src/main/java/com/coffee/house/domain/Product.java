package com.coffee.house.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Product {
    private final UUID productId;
    private String productName;
    private Category category;
    private long price;
    private String description;
    private final LocalDateTime createAt;
    private LocalDateTime updateAt;

    public void setPrice(long price) {
        this.price = price;
        this.updateAt = LocalDateTime.now();
    }

    public void changeCategory(Category category) {
        this.category = category;
        this.updateAt = LocalDateTime.now();

    }

    public void changeProductName(String productName) {
        this.productName = productName;
        this.updateAt = LocalDateTime.now();

    }

    public void changeDescription(String description) {
        this.description = description;
        this.updateAt = LocalDateTime.now();

    }
}
