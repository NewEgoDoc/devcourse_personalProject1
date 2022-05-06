package com.coffee.house.controller;

import com.coffee.house.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductRequest {
    private String productName;
    private Category category;
    private long price;
    private String description;
}
