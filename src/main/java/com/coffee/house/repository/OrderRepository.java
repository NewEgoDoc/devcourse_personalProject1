package com.coffee.house.repository;


import com.coffee.house.domain.Order;

import java.util.List;

public interface OrderRepository{
    public Order insert(Order order);
    public List<Order> selectAll();
    public void deleteAll();
}
