package com.charles.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication(scanBasePackages = "com.charles.springcloud")
public class Supplier2Application {
    public static void main(String[] args) {
        SpringApplication.run(Supplier2Application.class, args);
    }
}
