package com.charles.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wang on 17-3-16.
 */
@RestController
public class ConsumerFeignController {
    @Autowired
    public FeignClientService feignConsumer;

    @RequestMapping(value = "/addInFeign", method = RequestMethod.GET)
    public String add() {
        return feignConsumer.sayHello("request_from_feign");
    }

}
