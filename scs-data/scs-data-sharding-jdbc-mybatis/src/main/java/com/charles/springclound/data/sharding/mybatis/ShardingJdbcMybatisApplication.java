package com.charles.springclound.data.sharding.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class ShardingJdbcMybatisApplication {
    // CHECKSTYLE:OFF
    public static void main(final String[] args) {
        // CHECKSTYLE:ON
        SpringApplication.run(ShardingJdbcMybatisApplication.class, args);
        //ApplicationContext applicationContext = SpringApplication.run(ShardingJdbcMybatisApplication.class, args);
        //applicationContext.getBean(OrderService.class).demo();
    }
}
