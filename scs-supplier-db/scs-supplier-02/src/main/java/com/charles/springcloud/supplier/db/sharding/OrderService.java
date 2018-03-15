package com.charles.springcloud.supplier.db.sharding;

public interface OrderService {
    Order findOne(Long orderId);
}
