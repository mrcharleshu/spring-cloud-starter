package com.charles.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaSlave01Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaSlave01Application.class).web(true).run(args);
    }
}
