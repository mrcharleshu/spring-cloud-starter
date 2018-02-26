package com.charles.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
//@EnableCircuitBreaker
@EnableHystrix
@RibbonClient(name = "say-hello", configuration = SayHelloConfiguration.class)
public class UserApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApplication.class);
    // private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RibbonClientService ribbonClientService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
        // System.out.println("Count -- " + atomicInteger.incrementAndGet());
        return ribbonClientService.sayHello(name);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testLoadBalancer() {
        LOGGER.info("Request helloRibbon......");
        ServiceInstance instance = loadBalancerClient.choose("say-hello");
        LOGGER.info("Host={},Port={},Uri={},ServiceId={}",
                instance.getHost(), instance.getPort(), instance.getUri(), instance.getServiceId());
        return ribbonClientService.sayHello("TestAPI");
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

