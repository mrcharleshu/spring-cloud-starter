package com.charles.springcloud.supplier.db.sharding.jpa.repository;

import com.charles.springcloud.supplier.db.sharding.jpa.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
