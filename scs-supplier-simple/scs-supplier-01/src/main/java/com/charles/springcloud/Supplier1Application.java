package com.charles.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication(scanBasePackages = "com.charles.springcloud")
public class Supplier1Application {
    public static void main(String[] args) {
        SpringApplication.run(Supplier1Application.class, args);
    }
}
