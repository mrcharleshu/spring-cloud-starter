package com.charles.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errServiceFallback")
    public String sayHello() {
        String url = "http://SAY-HELLO/helloRibbon?name=request_from_ribbon";
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    public String errServiceFallback() {
        return "errorWithRibbon";
    }
}
