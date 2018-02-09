package com.charles.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaSlave02Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaSlave02Application.class).web(true).run(args);
    }
}
