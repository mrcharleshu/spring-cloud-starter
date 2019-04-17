package com.charles.springcloud.tracing.sleuth.controller;

import com.charles.springcloud.tracing.base.annotation.LogApiCall;
import com.charles.springcloud.tracing.base.constants.CustomizedMdcKeys;
import com.charles.springcloud.tracing.base.constants.ServiceNames;
import com.charles.springcloud.tracing.base.service.RemoteService;
import com.charles.springcloud.tracing.sleuth.annotation.LogActionStepTracer;
import com.charles.springcloud.tracing.sleuth.annotation.LogActionTracer;
import com.charles.springcloud.tracing.sleuth.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class SleuthHomeController implements ServiceNames {
    private static final Logger LOGGER = LoggerFactory.getLogger(SleuthHomeController.class);
    private Random random = new Random();
    private final Tracer tracer;
    private final SimpleService simpleService;
    private final RemoteService remoteService;
    private static final String XIAO_XIANG = "XiaoXiang";

    @Autowired
    public SleuthHomeController(Tracer tracer, SimpleService simpleService,
            @Qualifier(value = "sleuthRemoteServiceImpl") RemoteService remoteService) {
        this.tracer = tracer;
        this.simpleService = simpleService;
        this.remoteService = remoteService;
    }

    private String getBaggageValue() {
        return tracer.getCurrentSpan().getBaggageItem(CustomizedMdcKeys.ACTION);
    }

    private int sleep() throws InterruptedException {
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return sleep;
    }

    public void testAsync3() throws ExecutionException, InterruptedException {
        // Start the clock
        long start = System.currentTimeMillis();
        // Kick of multiple, asynchronous lookups
        CompletableFuture<String> result1 = simpleService.asyncCompletableFuture();
        CompletableFuture<String> result2 = simpleService.asyncCompletableFuture();
        CompletableFuture<String> result3 = simpleService.asyncCompletableFuture();
        // Wait until they are all done
        CompletableFuture.allOf(result1, result2, result3).join();
        // Print results, including elapsed time
        LOGGER.info("Elapsed time: " + (System.currentTimeMillis() - start));
        LOGGER.info("--> " + result1.get());
        LOGGER.info("--> " + result2.get());
        LOGGER.info("--> " + result3.get());
    }

    @LogApiCall(action = SERVICE_1)
    @LogActionTracer(action = XIAO_XIANG, continued = true)
    @LogActionStepTracer(step = "mainMethod")
    @GetMapping("start")
    public String start() throws InterruptedException, ExecutionException {
        LOGGER.info("start");
        int sleep = sleep();
        simpleService.testNewSpanMethod("testTagValue");
        simpleService.testParallelStream();
        simpleService.testAsync1();
        simpleService.testAsync2();
        testAsync3();
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
