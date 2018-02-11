package com.charles.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/feign")
@RestController
public class ConsumerFeignController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerFeignController.class);
    @Autowired
    public FeignClientService feignClientService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        LOGGER.info("Request helloFeign......");
        return feignClientService.sayHello("request_from_feign");
    }

}
