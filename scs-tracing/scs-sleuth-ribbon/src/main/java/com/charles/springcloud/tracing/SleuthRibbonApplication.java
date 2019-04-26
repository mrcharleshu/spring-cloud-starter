package com.charles.springcloud.tracing;

import com.charles.springcloud.stream.channel.InputChannel;
import com.charles.springcloud.stream.channel.OutputChannel;
import com.charles.springcloud.tracing.service.RemoteService;
import com.charles.springcloud.tracing.sleuth.service.impl.SleuthRibbonServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({InputChannel.class, OutputChannel.class})
public class SleuthRibbonApplication {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RemoteService remoteService(RestTemplate restTemplate) {
        return new SleuthRibbonServiceImpl(restTemplate);
    }

    public static void main(String[] args) {
        SpringApplication.run(SleuthRibbonApplication.class, args);
    }
}
