package com.charles.springcloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wang on 17-3-16.
 */
@FeignClient(value = "say-hello", fallback = FeignClientFallback.class)
public interface FeignClientService {
    @RequestMapping(method = RequestMethod.GET, value = "/sayHello")
    String sayHello(@RequestParam(value = "name") String g);
}
