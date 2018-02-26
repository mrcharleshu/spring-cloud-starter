package com.charles.springcloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "say-hello", fallback = FeignClientFallback.class)
public interface FeignClientService {
    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    String sayHello(@RequestParam(value = "name") String name);
}
