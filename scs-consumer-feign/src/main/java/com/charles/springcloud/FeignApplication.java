package com.charles.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

//@SpringCloudApplication
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@EnableHystrix
@ComponentScan("com.charles.springcloud")
public class FeignApplication {
    @Bean//定义REST客户端，RestTemplate实例
    @LoadBalanced//开启负债均衡的能力
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //@Bean
    //@Scope("prototype")
    //public Feign.Builder feignBuilder() {
    //    return Feign.builder();
    //}

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}
