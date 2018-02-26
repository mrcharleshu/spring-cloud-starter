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
 * (e.g., in this example, you could put SayHelloConfiguration in a different package from UserApplication,
 * such as 'com.charles.ribbon.SayHelloConfiguration', and @Configuration should be annotated).
 * </p>
 * <href>https://spring.io/guides/gs/client-side-load-balancing/</href>
 */
public class SayHelloConfiguration {
    @Autowired
    private IClientConfig config;

    /**
     * Our IPing is a PingUrl, which will ping a URL to check the status of each server.
     * Say Hello has, as you’ll recall, a method mapped to the / path;
     * that means that Ribbon will get an HTTP 200 response when it pings a running Say Hello server.
     * @param config
     * @return
     */
    @Bean
    public IPing ribbonPing(IClientConfig config) {
        System.out.println("IPing");
        return new PingUrl();
    }

    /**
     * The IRule we set up, the AvailabilityFilteringRule,
     * will use Ribbon’s built-in circuit breaker functionality to filter out any servers in an “open-circuit” state:
     * if a ping fails to connect to a given server, or if it gets a read failure for the server,
     * Ribbon will consider that server “dead” until it begins to respond normally.
     * @param config
     * @return
     */
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        System.out.println("IRule");
        return new AvailabilityFilteringRule();
    }
}