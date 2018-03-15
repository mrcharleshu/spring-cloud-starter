package com.charles.springcloud.supplier.db.sharding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("orders/{orderId}")
    public Order findOne(@PathVariable Long orderId) {
        return orderService.findOne(orderId);
    }
}
