package com.charles.springcloud.data.sharding.server;

import com.charles.springcloud.data.sharding.server.repository.OrderRepository;
import com.charles.springcloud.data.sharding.server.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {
    @Resource
    private OrderRepository orderRepository;

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
