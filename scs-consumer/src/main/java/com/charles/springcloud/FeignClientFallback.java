package com.charles.springcloud;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wang on 17-3-17.
 */
@Component
public class FeignClientFallback implements FeignClientService {
    @Override
    public String sayHello(@RequestParam(value = "name") String g) {
        return "Service not found";
    }
}
