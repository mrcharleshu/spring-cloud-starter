package com.charles.springcloud.supplier.db.sharding.server.repository;

import com.charles.springcloud.supplier.db.sharding.server.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
