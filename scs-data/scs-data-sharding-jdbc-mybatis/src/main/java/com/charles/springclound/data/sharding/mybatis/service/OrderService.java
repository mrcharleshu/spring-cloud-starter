package com.charles.springclound.data.sharding.mybatis.service;

import com.charles.springclound.data.sharding.mybatis.repository.OrderItemRepository;
import com.charles.springclound.data.sharding.mybatis.repository.OrderRepository;
import com.charles.springclound.data.sharding.mybatis.entity.Order;
import com.charles.springclound.data.sharding.mybatis.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;

    public void demo() {
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        List<Long> orderIds = new ArrayList<>(10);
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId(51);
            order.setStatus("INSERT_TEST");
            orderRepository.insert(order);
            long orderId = order.getOrderId();
            orderIds.add(orderId);

            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(51);
            item.setStatus("INSERT_TEST");
            orderItemRepository.insert(item);
        }
        System.out.println(orderItemRepository.selectAll());
        //System.out.println("2.Delete--------------");
        //for (Long each : orderIds) {
        //    orderRepository.delete(each);
        //    orderItemRepository.delete(each);
        //}
        //System.out.println(orderItemRepository.selectAll());
        // orderItemRepository.dropTable();
        // orderRepository.dropTable();
    }

    public Order findOne(final Integer userId, final Long orderId) {
        return orderRepository.findOne(userId, orderId);
    }

    public void deleteOne(final Long orderId) {
        orderRepository.delete(orderId);
    }
}
