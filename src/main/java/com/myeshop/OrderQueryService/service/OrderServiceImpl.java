package com.myeshop.OrderQueryService.service;

import com.myeshop.Core.order.event.OrderCreatedEvent;
import com.myeshop.Core.order.rest.CartItem;
import com.myeshop.OrderQueryService.data.entity.Order;
import com.myeshop.OrderQueryService.data.entity.OrderItem;
import com.myeshop.OrderQueryService.data.repository.OrderItemRepository;
import com.myeshop.OrderQueryService.data.repository.OrderRepository;
import com.myeshop.OrderQueryService.data.rest.OrderItemRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void createOrder(OrderCreatedEvent event) {
        Order order = new Order(event.getOrderId());
        BeanUtils.copyProperties(event, order);

        for(CartItem cartItem : event.getCartItems()) {
            OrderItem oi = new OrderItem();
            BeanUtils.copyProperties(cartItem, oi);
            order.add(oi);
            }
        LOGGER.info("Create OrderItem");
        orderRepository.save(order);
    }

    @Override
    public List<OrderItemRest> findOrderItem(String orderId) {
        List<OrderItemRest> ans = new ArrayList<>();
        List<OrderItem> orderItemList = orderItemRepository.findByOrderId(orderId);

        for (OrderItem orderItem: orderItemList) {
            OrderItemRest item = new OrderItemRest();
            BeanUtils.copyProperties(orderItem, item);
            ans.add(item);
        }
        return ans;
    }
}
