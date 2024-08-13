package com.loy.t1aop.service;

import com.loy.t1aop.domain.Order;
import com.loy.t1aop.domain.OrderStatus;
import com.loy.t1aop.domain.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public void beforeEach() {
        user = new User(null, "Danil", "danil@mail.ru", null);
        userService.save(user);
    }

    @Test
    @Transactional
    public void save_whenValidOrder_saveSuccess() {
        Order order = new Order();
        order.setStatus(OrderStatus.COMPLETED);
        order.setDescription("Some decrip");
        order.setUser(user);
        orderService.save(order);
        Optional<Order> orderById = orderService.findById(order.getId());

        Assertions.assertTrue(orderById.isPresent());
    }

}
