package com.charles.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
//@SpringCloudApplication
//@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@EnableTurbine
@ComponentScan("com.charles.springcloud")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
