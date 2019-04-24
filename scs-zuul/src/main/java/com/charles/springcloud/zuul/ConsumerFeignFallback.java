package com.charles.springcloud.zuul;

import org.springframework.stereotype.Component;

@Component
public class ConsumerFeignFallback extends CustomizedFallbackProvider {
    private static final String SERVICE_NAME = "scs-consumer-feign";

    @Override
    public String getRoute() {
        return SERVICE_NAME;
    }
}