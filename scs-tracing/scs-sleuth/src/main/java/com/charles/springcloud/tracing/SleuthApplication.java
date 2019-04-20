package com.charles.springcloud.tracing;

import com.charles.springcloud.stream.channel.InputChannel;
import com.charles.springcloud.stream.channel.OutputChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableBinding({InputChannel.class, OutputChannel.class})
public class SleuthApplication {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //@Bean
    //public Executor taskExecutor() {
    //    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //    executor.setCorePoolSize(2);
    //    executor.setMaxPoolSize(2);
    //    executor.setQueueCapacity(500);
    //    executor.setThreadNamePrefix("AsyncExecutor-");
    //    executor.initialize();
    //    return executor;
    //}

    public static void main(String[] args) {
        SpringApplication.run(SleuthApplication.class, args);
    }
}
