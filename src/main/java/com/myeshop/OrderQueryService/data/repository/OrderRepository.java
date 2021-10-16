package com.myeshop.OrderQueryService.data.repository;

import com.myeshop.OrderQueryService.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findOrderById(String id);

    List<Order> findOrderByCustomerEmail(String email);
}
