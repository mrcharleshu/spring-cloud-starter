package com.charles.springcloud.data.sharding.jpa.repository;

import com.charles.springcloud.data.sharding.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserIdAndOrderId(Integer userId, Long orderId);
}
