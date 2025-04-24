package com.charles.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用类似
 * restTemplate.getForEntity("http://SAY-HELLO/sayHello?name=request_from_ribbo", String.class).getBody()
 * 这样的语句进行服务间调用并非不可以，只是我们在服务化的过程中，希望跨服务调用能够看起来像本地调用，这也是我理解的Feign的使用场景。
 */
@FeignClient(value = "say-hello", fallback = FeignClientFallback.class)
public interface FeignClientService {
    
    @GetMapping("/sayHello")
    String sayHello(@RequestParam(value = "name") String name);
}
