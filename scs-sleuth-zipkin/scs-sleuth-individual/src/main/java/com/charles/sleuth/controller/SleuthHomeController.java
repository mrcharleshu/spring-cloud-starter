package com.charles.sleuth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class SleuthHomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SleuthHomeController.class);
    private static final String SERVICE_2_API_URL = "http://SERVICE-2/foo";
    private static final String SERVICE_3_API_URL = "http://SERVICE-3/bar";
    private static final String SERVICE_4_API_URL = "http://SERVICE-4/tar";
    private Random random = new Random();
    private final RestTemplate restTemplate;

    @Autowired
    public SleuthHomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * service-1
     */
    @RequestMapping("start")
    public String start() throws InterruptedException {
        LOGGER.info("start");
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        LOGGER.info("执行调用");
        // String url = "http://SAY-HELLO/sayHello?name=request_from_ribbon";
        // return restTemplate.getForEntity(url, String.class).getBody();
        String response = restTemplate.getForEntity(SERVICE_2_API_URL, String.class).getBody();
        LOGGER.info("调用结束");
        return " [service1 sleep " + sleep + " ms]" + response;
    }

    /**
     * service-2
     */
    @RequestMapping("foo")
    public String foo() throws InterruptedException {
        LOGGER.info("foo");
        LOGGER.info("执行调用");
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        String response3 = restTemplate.getForEntity(SERVICE_3_API_URL, String.class).getBody();
        String response4 = restTemplate.getForEntity(SERVICE_4_API_URL, String.class).getBody();
        LOGGER.info("调用结束");
        return " [service2 sleep " + sleep + " ms]" + response3 + response4;
    }

    /**
     * service-3
     */
    @RequestMapping("bar")
    public String bar() throws InterruptedException {
        LOGGER.info("bar");
        LOGGER.info("执行调用");
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        LOGGER.info("调用结束");
        return " [service3 sleep " + sleep + " ms]";
    }

    /**
     * service-4
     */
    @RequestMapping("tar")
    public String tar() throws InterruptedException {
        LOGGER.info("tar");
        LOGGER.info("执行调用");
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        LOGGER.info("调用结束");
        return " [service4 sleep " + sleep + " ms]";
    }
}
