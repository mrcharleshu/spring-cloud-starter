package com.charles.springcloud.data.sharding.jpa.repository;

import com.charles.springcloud.data.sharding.jpa.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
