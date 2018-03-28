package com.charles.springcloud.supplier.db.sharding.jpa.service;

import com.charles.springcloud.supplier.db.sharding.jpa.entity.Order;
import com.charles.springcloud.supplier.db.sharding.jpa.entity.OrderItem;
import com.charles.springcloud.supplier.db.sharding.jpa.repository.OrderItemRepository;
import com.charles.springcloud.supplier.db.sharding.jpa.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private static final Integer DEFAULT_USER_ID = 51;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;

    public void demo() {
        List<Long> orderIds = new ArrayList<>(10);
        List<Long> orderItemIds = new ArrayList<>(10);
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId(DEFAULT_USER_ID);
            order.setStatus("INSERT_TEST");
            long orderId = orderRepository.save(order).getOrderId();
            orderIds.add(orderId);
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(DEFAULT_USER_ID);
            orderItemIds.add(orderItemRepository.save(item).getOrderItemId());
        }
//        List<OrderItem> orderItems = orderItemRepository.findAll();
//        System.out.println(orderItemRepository.findAll());
//        System.out.println("2.Delete--------------");
//        if (orderItems.size() > 0) {
//            for (Long each : orderItemIds) {
//                orderItemRepository.delete(each);
//            }
//            for (Long each : orderIds) {
//                orderRepository.delete(each);
//            }
//        }
//        System.out.println(orderItemRepository.findAll());
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Page<Order> findPage(final Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order findOne(final Integer userId, final Long orderId) {
        return orderRepository.findByUserIdAndOrderId(userId, orderId);
    }
}
