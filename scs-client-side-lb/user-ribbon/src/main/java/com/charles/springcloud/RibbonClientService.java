package com.charles.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonClientService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errServiceFallback")
    public String sayHello(final String name) {
        String url = "http://say-hello/greeting?name=" + name;
        return restTemplate.getForObject(url, String.class);
    }

    public String errServiceFallback(final String name, final Throwable e) {
        return String.format("Error with ribbon, request params[%s], cause: ", name, e.getMessage());
    }
}
