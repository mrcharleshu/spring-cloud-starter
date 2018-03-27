package io.shardingjdbc.example.orchestration.spring.boot.jpa;

import io.shardingjdbc.example.orchestration.spring.boot.jpa.entity.Order;
import io.shardingjdbc.example.orchestration.spring.boot.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("orders")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public Object findAll(@RequestParam(required = false) Integer pn,
            @RequestParam(required = false) Integer ps) {
        if (pn != null) {
            Pageable pageable = new PageRequest(pn,ps);
            return orderService.findPage(pageable);
        }
        return orderService.findAll();
    }
}
