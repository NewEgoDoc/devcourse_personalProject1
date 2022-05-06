package com.coffee.house.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void makeOrderObject(){
        UUID id = UUID.randomUUID();
        Email email = new Email("aaa@gmail.com");
        List<OrderItem> items = new ArrayList<>();
        LocalDateTime createdAt = LocalDateTime.now();

        Order order = new Order(id,email,items,createdAt);
        assertEquals(id, order.getOrderId());
        assertEquals(email.getAddress(), order.getEmail().getAddress());
        assertEquals(id, order.getOrderId());
        assertEquals(id, order.getOrderId());
    }

    @Test
    public void test_updateAddress(){
        UUID id = UUID.randomUUID();
        Email email = new Email("aaa@gmail.com");
        List<OrderItem> items = new ArrayList<>();
        LocalDateTime createdAt = LocalDateTime.now();
        String address = "Seoul";

        Order order = new Order(id,email,items,createdAt);
        order.updateAddress(address);

        assertEquals(address,order.getAddress());
    }

    @Test
    public void test_updatePostcode(){
        UUID id = UUID.randomUUID();
        Email email = new Email("aaa@gmail.com");
        List<OrderItem> items = new ArrayList<>();
        LocalDateTime createdAt = LocalDateTime.now();
        String postcode = "123123";

        Order order = new Order(id,email,items,createdAt);
        order.updatePostcode(postcode);

        assertEquals(postcode,order.getPostcode());
    }

    @Test
    public void test_updateOrderStatus(){
        UUID id = UUID.randomUUID();
        Email email = new Email("aaa@gmail.com");
        List<OrderItem> items = new ArrayList<>();
        LocalDateTime createdAt = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ACCEPTED;

        Order order = new Order(id,email,items,createdAt);
        order.updateOrderStatus(orderStatus);

        assertEquals(orderStatus,order.getOrderStatus());
    }

}