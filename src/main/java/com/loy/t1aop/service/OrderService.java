package com.loy.t1aop.service;

import com.loy.t1aop.domain.Order;

import java.util.Optional;

public interface OrderService {

    Order save(Order order);

    Optional<Order> findById(Long id);

    Order update(Order order);

    void delete(Order order);
}
