package com.charles.springcloud.tracing.sleuth.controller;

import com.charles.springcloud.tracing.annotation.LogApiCall;
import com.charles.springcloud.tracing.constants.CustomizedMdcKeys;
import com.charles.springcloud.tracing.constants.ServiceNames;
import com.charles.springcloud.tracing.service.RemoteService;
import com.charles.springcloud.tracing.sleuth.annotation.LogActionStepTracer;
import com.charles.springcloud.tracing.sleuth.annotation.LogActionTracer;
import com.charles.springcloud.tracing.sleuth.event.PublishEventService;
import com.charles.springcloud.tracing.sleuth.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class SleuthHomeController implements ServiceNames {
    private static final Logger LOGGER = LoggerFactory.getLogger(SleuthHomeController.class);
    private static final Random random = new Random();
    private static final String XIAO_XIANG = "XiaoXiang";
    private final Tracer tracer;
    private final SimpleService simpleService;
    private final RemoteService remoteService;
    private final PublishEventService publishEventService;

    @Autowired
    public SleuthHomeController(Tracer tracer, SimpleService simpleService,
            RemoteService remoteService, PublishEventService publishEventService) {
        this.tracer = tracer;
        this.simpleService = simpleService;
        this.remoteService = remoteService;
        this.publishEventService = publishEventService;
    }

    private String getBaggageValue() {
        return tracer.getCurrentSpan().getBaggageItem(CustomizedMdcKeys.ACTION);
    }

    private int sleep() throws InterruptedException {
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return sleep;
    }

    @LogApiCall(action = SERVICE_1)
    @LogActionTracer(action = XIAO_XIANG, continued = true)
    @LogActionStepTracer(step = "mainMethod")
    @GetMapping("start")
    public String start() throws InterruptedException, ExecutionException {
        LOGGER.info("start");
        int sleep = sleep();
        simpleService.testNewSpanMethod("testTagValue");
        // simpleService.testParallelStream();
        simpleService.testAsync1();
        simpleService.testAsync2();
        simpleService.testAsync3();
        publishEventService.testSimpleEvent();
        publishEventService.testEventWithReturnValue();
        String response = remoteService.callService2();
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_1, getBaggageValue(), sleep) + response;
    }

    @GetMapping("reentrant")
    @LogActionTracer(action = XIAO_XIANG)
    public String reentrant() throws InterruptedException {
        LOGGER.info("reentrant");
        int sleep = sleep();
        String response = remoteService.callService4();
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_1, getBaggageValue(), sleep) + response;
    }

    @GetMapping("foo")
    public String foo() throws InterruptedException {
        LOGGER.info("foo");
        int sleep = sleep();
        // String response3 = "fake service3 call";
        // remoteService.callService3Async();
        String response3 = remoteService.callService3();
        String response4 = remoteService.callService4();
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_2, getBaggageValue(), sleep) + response3 + response4;
    }

    @GetMapping("bar")
    public String bar() throws InterruptedException {
        LOGGER.info("bar");
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_3, getBaggageValue(), sleep());
    }

    @GetMapping("tar")
    public String tar() throws InterruptedException {
        LOGGER.info("tar");
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_4, getBaggageValue(), sleep());
    }
}
