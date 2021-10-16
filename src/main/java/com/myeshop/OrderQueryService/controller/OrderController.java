package com.myeshop.OrderQueryService.controller;

import com.myeshop.OrderQueryService.data.entity.Order;
import com.myeshop.OrderQueryService.data.repository.OrderRepository;
import com.myeshop.OrderQueryService.data.rest.OrderItemRest;
import com.myeshop.OrderQueryService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order") //  http://localhost:8082/order-query-service/order
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/history")
    public List<Order> findOrder(@RequestParam("email") String email) {
        return orderRepository.findOrderByCustomerEmail(email);
    }

    @GetMapping("/orderItem")
    public List<OrderItemRest> findOrderItem(@RequestParam("orderId") String orderId) {
        return orderService.findOrderItem(orderId);
    }
}
