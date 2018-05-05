package com.charles.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaMasterApplication {
    public static void main(String[] args) {
        //new SpringApplicationBuilder(EurekaMasterApplication.class).web(true).run(args);
        SpringApplication.run(EurekaMasterApplication.class, args);
    }
}
