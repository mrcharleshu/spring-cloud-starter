package com.charles.springcloud;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class SayHelloConfiguration {
    @Autowired
    private IClientConfig config;

    @Bean
    public IPing ribbonPing(IClientConfig config) {
        System.out.println("IPing");
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        System.out.println("IRule");
        return new AvailabilityFilteringRule();
    }
}