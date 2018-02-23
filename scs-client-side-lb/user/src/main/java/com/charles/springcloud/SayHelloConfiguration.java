package com.charles.springcloud;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * The @SpringBootApplication annotation on the UserApplication class is equivalent to (among others) the
 * @Configuration annotation that marks a class as a source of bean definitions.
 * This is why we don’t need to annotate the SayHelloConfiguration class with @Configuration:
 * since it’s in the same package as UserApplication, it is already being scanned for bean methods.
 * </p>
 * <p>
 * This approach does mean that our Ribbon configuration will be part of the main application context and
 * therefore shared by all Ribbon clients in the User application.
 * In a normal application, you can avoid this by keeping Ribbon beans out of the main application context
 * (e.g., in this example, you could put SayHelloConfiguration in a different package from UserApplication).
 * </p>
 */
public class SayHelloConfiguration {
    @Autowired
    private IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new AvailabilityFilteringRule();
    }
}