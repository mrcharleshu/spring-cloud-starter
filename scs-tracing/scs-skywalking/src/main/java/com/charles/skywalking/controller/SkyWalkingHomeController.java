package com.charles.skywalking.controller;

import com.charles.skywalking.service.RemoteService;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.charles.skywalking.constants.ServiceNames.SERVICE_1;
import static com.charles.skywalking.constants.ServiceNames.SERVICE_2;
import static com.charles.skywalking.constants.ServiceNames.SERVICE_3;
import static com.charles.skywalking.constants.ServiceNames.SERVICE_4;

@RestController
@RequestMapping("/")
public class SkyWalkingHomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkyWalkingHomeController.class);
    private Random random = new Random();
    private final RemoteService remoteService;

    @Autowired
    public SkyWalkingHomeController(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    private int sleep() throws InterruptedException {
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return sleep;
    }

    @Trace
    @GetMapping("start")
    public String start() throws InterruptedException {
        ActiveSpan.tag("tag-1", "销项tag1");
        ActiveSpan.tag("tag-2", "销项tag2");
        ActiveSpan.tag("tag-3", "销项tag3");
        LOGGER.info("SkyWalking traceId = {}", TraceContext.traceId());
        LOGGER.info("start");
        int sleep = sleep();
        String response = remoteService.callService2();
        return String.format(" [%s sleep %s ms]", SERVICE_1, sleep) + response;
    }

    @GetMapping("foo")
    public String foo() throws InterruptedException {
        LOGGER.info("foo");
        int sleep = sleep();
        String response3 = remoteService.callService3();
        String response4 = remoteService.callService4();
        return String.format(" [%s sleep %s ms]", SERVICE_2, sleep) + response3 + response4;
    }

    @GetMapping("bar")
    public String bar() throws InterruptedException {
        LOGGER.info("bar");
        return String.format(" [%s sleep %s ms]", SERVICE_3, sleep());
    }

    @GetMapping("tar")
    public String tar() throws InterruptedException {
        LOGGER.info("tar");
        return String.format(" [%s sleep %s ms]", SERVICE_4, sleep());
    }
}
