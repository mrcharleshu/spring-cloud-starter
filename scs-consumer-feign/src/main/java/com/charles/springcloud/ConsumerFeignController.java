package com.charles.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/feign")
@RestController
public class ConsumerFeignController {
    @Autowired
    public FeignClientService feignClientService;

    @GetMapping("/hello")
    public String sayHello() {
        log.info("Request helloFeign......");
        return feignClientService.sayHello("request_from_feign");
    }
}
