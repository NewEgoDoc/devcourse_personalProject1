package com.coffee.house.repository;


import com.coffee.house.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.coffee.house.Utils.toLocalDateTime;
import static com.coffee.house.Utils.toUUID;

@Repository
@RequiredArgsConstructor
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Order insert(Order order) {
        jdbcTemplate.update("INSERT INTO orders(order_id, email, address, postcode, order_status, created_at, updated_at)" +
                "VALUES(UUID_TO_BIN(:orderId), :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)",toOrderParamMap(order));
        order.getOrderItems().forEach(item -> jdbcTemplate.update(
                "INSERT INTO order_items(order_id, product_id, category, price, quantity,created_at, updated_at) " +
                "VALUES(UUID_TO_BIN(:orderId), UUID_TO_BIN(:productId), :category, :price, :quantity, :createdAt, :updatedAt)",
                toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), LocalDateTime.now(),item)));
        return order;
    }

    @Override
    public List<Order> selectAll() {
        return jdbcTemplate.query("SELECT * FROM orders", productRowMapper);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM orders",Collections.emptyMap());
    }


    private Map<String, Object> toOrderParamMap(Order order){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId",order.getOrderId().toString().getBytes());
        paramMap.put("email",order.getEmail().getAddress());
        paramMap.put("address",order.getAddress());
        paramMap.put("postcode",order.getPostcode());
        paramMap.put("orderStatus",order.getOrderStatus().toString());
        paramMap.put("createdAt",order.getCreatedAt());
        paramMap.put("updatedAt",order.getUpdatedAt());
        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderItem item){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId",orderId.toString().getBytes());
        paramMap.put("productId",item.getProductId().toString().getBytes());
        paramMap.put("category",item.getCategory().toString());
        paramMap.put("price",item.getPrice());
        paramMap.put("quantity",item.getQuantity());
        paramMap.put("createdAt",createdAt);
        paramMap.put("updatedAt",updatedAt);
        return paramMap;
    }

    static final RowMapper<Order> productRowMapper = (resultSet, i) -> {
        UUID orderId = toUUID(resultSet.getBytes("order_id"));
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String postcode = resultSet.getString("postcode");
        OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Order(orderId,new Email(email),address,postcode,null,orderStatus,createdAt,updatedAt);
    };

}
