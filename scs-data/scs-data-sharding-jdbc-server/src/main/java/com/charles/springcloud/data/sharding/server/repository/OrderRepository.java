package com.charles.springcloud.data.sharding.server.repository;

import com.charles.springcloud.data.sharding.server.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserIdAndOrderId(Integer userId, Long orderId);
}
