package com.charles.springcloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用类似
 * restTemplate.getForEntity("http://SAY-HELLO/sayHello?name=request_from_ribbo", String.class).getBody()
 * 这样的语句进行服务间调用并非不可以，只是我们在服务化的过程中，希望跨服务调用能够看起来像本地调用，这也是我理解的Feign的使用场景。
 */
@FeignClient(value = "say-hello", fallback = FeignClientFallback.class)
public interface FeignClientService {
    @RequestMapping(method = RequestMethod.GET, value = "/sayHello")
    String sayHello(@RequestParam(value = "name") String name);
}
