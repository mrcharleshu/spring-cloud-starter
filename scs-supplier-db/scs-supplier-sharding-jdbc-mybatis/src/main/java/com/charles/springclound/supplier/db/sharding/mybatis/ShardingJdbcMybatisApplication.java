package com.charles.springclound.supplier.db.sharding.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShardingJdbcMybatisApplication {
    // CHECKSTYLE:OFF
    public static void main(final String[] args) {
        // CHECKSTYLE:ON
        SpringApplication.run(ShardingJdbcMybatisApplication.class, args);
        //ApplicationContext applicationContext = SpringApplication.run(ShardingJdbcMybatisApplication.class, args);
        //applicationContext.getBean(OrderService.class).demo();
    }
}
