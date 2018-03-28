package com.charles.springcloud.supplier.db.sharding.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class ShardingJdbcJpaApplication {
    // CHECKSTYLE:OFF
    public static void main(final String[] args) {
        // CHECKSTYLE:ON
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(ShardingJdbcJpaApplication.class, args);
        //ApplicationContext applicationContext = SpringApplication.run(SpringBootDataJpaMain.class, args);
        //applicationContext.getBean(OrderService.class).demo();
    }
}
