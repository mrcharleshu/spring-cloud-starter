package com.charles.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wang on 17-3-17.
 */
@Service
public class RibbonService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errServiceFallback")
    public String sayHello() {
        return restTemplate.getForEntity("http://SAY-HELLO/sayHello?name=request_from_ribbon", String.class).getBody();
    }

    public String errServiceFallback() {
        return "errorWithRibbon";
    }
}
