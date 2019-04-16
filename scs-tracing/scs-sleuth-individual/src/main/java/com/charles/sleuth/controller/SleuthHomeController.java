package com.charles.sleuth.controller;

import com.charles.sleuth.annotation.LogActionStepTracer;
import com.charles.sleuth.annotation.LogActionTracer;
import com.charles.sleuth.annotation.ServiceApiCall;
import com.charles.sleuth.constants.CustomizedMdcKeys;
import com.charles.sleuth.constants.ServiceNames;
import com.charles.sleuth.service.RemoteService;
import com.charles.sleuth.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final SimpleService simpleService;
    private final RemoteService remoteService;
    private final Tracer tracer;
    private static final String XIAO_XIANG = "XiaoXiang";

    @Autowired
    public SleuthHomeController(SimpleService simpleService, RemoteService remoteService, Tracer tracer) {
        this.simpleService = simpleService;
        this.remoteService = remoteService;
        this.tracer = tracer;
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

    @ServiceApiCall(service = SERVICE_1)
    @LogActionTracer(action = XIAO_XIANG, continued = true)
    @LogActionStepTracer(step = "mainMethod")
    @GetMapping("start")
    public String start() throws InterruptedException, ExecutionException {
        LOGGER.info("start");
        int sleep = sleep();
        simpleService.testParallelStream();
        simpleService.testAsync1();
        simpleService.testAsync2();
        testAsync3();
        String response = remoteService.callService2();
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_1, getBaggageValue(), sleep) + response;
    }

    @ServiceApiCall(service = SERVICE_1)
    @GetMapping("reentrant")
    @LogActionTracer(action = XIAO_XIANG)
    public String reentrant() throws InterruptedException {
        LOGGER.info("reentrant");
        int sleep = sleep();
        String response = remoteService.callService4();
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_1, getBaggageValue(), sleep) + response;
    }

    @ServiceApiCall(service = SERVICE_2)
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

    @ServiceApiCall(service = SERVICE_3)
    @GetMapping("bar")
    public String bar() throws InterruptedException {
        LOGGER.info("bar");
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_3, getBaggageValue(), sleep());
    }

    @ServiceApiCall(service = SERVICE_4)
    @GetMapping("tar")
    public String tar() throws InterruptedException {
        LOGGER.info("tar");
        return String.format(" [%s (%s) sleep %s ms]", SERVICE_4, getBaggageValue(), sleep());
    }
}
