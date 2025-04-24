package com.charles.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


//@EnableDiscoveryClient
//@EnableCircuitBreaker
//@SpringCloudApplication
//@EnableFeignClients
@EnableHystrixDashboard
@EnableHystrix
@EnableTurbine
@SpringBootApplication(scanBasePackages = "com.charles.springcloud")
public class HystrixMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixMonitorApplication.class, args);
    }
}
