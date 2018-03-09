package com.charles.springcloud;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeController {
    private static final Logger LOGGER = Logger.getLogger(ComputeController.class);
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(@RequestParam String name) {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        // waitSeveralSeconds();
        String r = "hello," + name;
        LOGGER.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    private void waitSeveralSeconds() {
        try {
            // 模拟处理很长时，测试hy
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
