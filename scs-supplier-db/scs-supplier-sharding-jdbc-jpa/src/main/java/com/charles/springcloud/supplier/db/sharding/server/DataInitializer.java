package com.charles.springcloud.supplier.db.sharding.server;

import com.charles.springcloud.supplier.db.sharding.server.entity.Order;
import com.charles.springcloud.supplier.db.sharding.server.entity.OrderItem;
import com.charles.springcloud.supplier.db.sharding.server.repository.OrderItemRepository;
import com.charles.springcloud.supplier.db.sharding.server.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner, Ordered {
    private static final Integer DEFAULT_USER_ID = 50;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(final String... strings) throws Exception {
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

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
