package com.charles.springcloud.supplier.db.sharding.jpa;

import com.charles.springcloud.supplier.db.sharding.jpa.entity.Order;
import com.charles.springcloud.supplier.db.sharding.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "orders")
    public Object findAll(@RequestParam(required = false) Integer pn,
            @RequestParam(required = false) Integer ps) {
        if (pn != null) {
            Pageable pageable = new PageRequest(pn, ps);
            return orderService.findPage(pageable);
        }
        return orderService.findAll();
    }

    @GetMapping("users/{userId}/orders/{orderId}")
    public Order findOne(@PathVariable Integer userId, @PathVariable Long orderId) {
        return orderService.findOne(userId, orderId);
    }
}
