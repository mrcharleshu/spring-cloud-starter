package com.charles.springcloud.stream;

import com.charles.springcloud.stream.channel.OutputChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({OutputChannel.class})
public class StreamJmsActiveMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamJmsActiveMQApplication.class, args);
    }
}
