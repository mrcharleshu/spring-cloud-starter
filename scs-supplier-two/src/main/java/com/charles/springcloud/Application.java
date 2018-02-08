package com.charles.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by wang on 17-3-12.
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.charles")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
