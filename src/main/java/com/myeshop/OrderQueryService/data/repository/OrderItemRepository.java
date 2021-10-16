package com.myeshop.OrderQueryService.data.repository;

import com.myeshop.OrderQueryService.data.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(String orderId);
}
