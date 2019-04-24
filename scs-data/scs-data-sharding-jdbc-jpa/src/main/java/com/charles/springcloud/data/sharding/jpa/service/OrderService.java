package com.charles.springcloud.data.sharding.jpa.service;

import com.charles.springcloud.data.sharding.jpa.entity.Order;
import com.charles.springcloud.data.sharding.jpa.repository.OrderRepository;
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
