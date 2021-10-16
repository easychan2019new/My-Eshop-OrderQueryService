package com.myeshop.OrderQueryService.query;

import com.myeshop.Core.order.event.OrderApprovedEvent;
import com.myeshop.Core.order.event.OrderCanceledEvent;
import com.myeshop.Core.order.event.OrderCreatedEvent;
import com.myeshop.Core.order.event.OrderRejectedEvent;
import com.myeshop.OrderQueryService.data.entity.Order;
import com.myeshop.OrderQueryService.data.repository.OrderRepository;
import com.myeshop.OrderQueryService.service.OrderService;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventHandler.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        LOGGER.info("Handle orderCreatedEvent!");
        orderService.createOrder(orderCreatedEvent);
    }

    @EventHandler
    public void on(OrderCanceledEvent orderCanceledEvent) {
        LOGGER.info("Handle orderCanceledEvent!");
        Order order = orderRepository.findOrderById(orderCanceledEvent.getOrderId());
        order.setStatus(orderCanceledEvent.getStatus());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderApprovedEvent orderApprovedEvent) {
        LOGGER.info("Handle orderApprovedEvent!");
        Order order = orderRepository.findOrderById(orderApprovedEvent.getOrderId());
        order.setStatus(orderApprovedEvent.getStatus());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderRejectedEvent orderRejectedEvent) {
        LOGGER.info("Handle orderRejectedEvent!");
        Order order = orderRepository.findOrderById(orderRejectedEvent.getOrderId());
        order.setStatus(orderRejectedEvent.getStatus());
        orderRepository.save(order);
    }
}
