package com.charles.springcloud;

import org.springframework.stereotype.Component;

@Component
public class ConsumerRibbonFallback extends CustomizedFallbackProvider {
    private static final String SERVICE_NAME = "scs-consumer-ribbon";

    @Override
    public String getRoute() {
        return SERVICE_NAME;
    }
}