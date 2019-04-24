package com.charles.springclound.data.sharding.mybatis;

import com.charles.springclound.data.sharding.mybatis.entity.Order;
import com.charles.springclound.data.sharding.mybatis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("users/{userId}/orders/{orderId}")
    public Order findOne(@PathVariable Integer userId, @PathVariable Long orderId) {
        return orderService.findOne(userId, orderId);
    }

    @DeleteMapping("users/{userId}/orders/{orderId}")
    public Boolean deleteOne(@PathVariable Integer userId, @PathVariable Long orderId) {
        orderService.deleteOne(orderId);
        return true;
    }
}
