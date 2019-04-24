package com.charles.springclound.data.sharding.mybatis;

import com.charles.springclound.data.sharding.mybatis.entity.Order;
import com.charles.springclound.data.sharding.mybatis.entity.OrderItem;
import com.charles.springclound.data.sharding.mybatis.repository.OrderItemRepository;
import com.charles.springclound.data.sharding.mybatis.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner, Ordered {
    private static final Integer DEFAULT_USER_ID = 51;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(final String... strings) throws Exception {
        orderItemRepository.dropTable();
        orderRepository.dropTable();
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        List<Long> orderIds = new ArrayList<>(10);
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId(DEFAULT_USER_ID);
            order.setStatus("INSERT_TEST");
            orderRepository.insert(order);
            long orderId = order.getOrderId();
            orderIds.add(orderId);

            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(DEFAULT_USER_ID);
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

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
