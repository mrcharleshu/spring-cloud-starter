package com.charles.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerFeignController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerFeignController.class);
    @Autowired
    public FeignClientService feignClientService;

    @RequestMapping(value = "/addInFeign", method = RequestMethod.GET)
    public String sayHello() {
        LOGGER.info("Request addInFeign......");
        return feignClientService.sayHello("request_from_feign");
    }

}
