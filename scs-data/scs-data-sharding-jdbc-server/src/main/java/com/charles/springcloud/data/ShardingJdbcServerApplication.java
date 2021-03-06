package com.charles.springcloud.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class ShardingJdbcServerApplication {
    
    public static void main(final String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(ShardingJdbcServerApplication.class, args);
    }
}
