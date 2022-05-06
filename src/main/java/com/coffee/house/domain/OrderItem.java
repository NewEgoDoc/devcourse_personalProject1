package com.coffee.house.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderItem {
    private UUID productId;
    private Category category;
    private long price;
    private int quantity;
}
