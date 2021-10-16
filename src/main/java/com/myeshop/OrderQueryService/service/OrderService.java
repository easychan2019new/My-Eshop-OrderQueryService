package com.myeshop.OrderQueryService.service;

import com.myeshop.Core.order.event.OrderCreatedEvent;
import com.myeshop.OrderQueryService.data.rest.OrderItemRest;

import java.util.List;

public interface OrderService {
    void createOrder(OrderCreatedEvent event);
    List<OrderItemRest> findOrderItem(String orderId);
}
