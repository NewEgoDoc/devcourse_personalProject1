package com.coffee.house.repository;

import com.coffee.house.domain.Email;
import com.coffee.house.domain.Order;
import com.coffee.house.domain.OrderStatus;
import com.coffee.house.domain.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderJdbcRepositoryTest {

    @Autowired
    OrderRepository repository;

    private final Order order = new Order(
            UUID.randomUUID(),
            new Email("aaaa@naver.com"),
            "address",
            "123123",
            new ArrayList<>(),
            OrderStatus.ACCEPTED,
            LocalDateTime.now(),
            LocalDateTime.now());

    @Test
    @org.junit.jupiter.api.Order(1)
    public void 오더를_추가할_수_있다(){
        repository.insert(order);
        List<Order> all = repository.selectAll();
        assertThat(all.isEmpty(), is(false));
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void 오더_전체를_삭제한다(){
        repository.deleteAll();
        List<Order> all = repository.selectAll();
        assertThat(all.isEmpty(),is(true));
    }

}