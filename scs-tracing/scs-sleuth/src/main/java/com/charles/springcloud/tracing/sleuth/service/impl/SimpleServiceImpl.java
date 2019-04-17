package com.charles.springcloud.tracing.sleuth.service.impl;

import com.charles.springcloud.tracing.sleuth.annotation.LogActionStepTracer;
import com.charles.springcloud.tracing.sleuth.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class SimpleServiceImpl implements SimpleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServiceImpl.class);
    @NewSpan(name = "testNewSpanMethod")
    @Override
    public void testNewSpanMethod(@SpanTag("testTag") String testTag) {
        LOGGER.info("This is a new span, testTag = {}", testTag);
    }

    @Async
    public void asyncMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        LOGGER.info("Async Method");
    }

    @LogActionStepTracer(step = "testAsync1")
    @Async
    @Override
    public void testAsync1() throws InterruptedException {
        LOGGER.info("Start Async Method");
        asyncMethod();
        LOGGER.info("End Async Method");
    }

    @Async
    protected void print(String str) {
        LOGGER.info("Simple print string: {}", str);
    }

    @LogActionStepTracer(step = "testAsync2")
    @Override
    public void testAsync2() {
        Arrays.asList("Tom", "Jerry", "Mary", "Colin").forEach(this::print);
    }

    @Override
    public void testAsync3() throws ExecutionException, InterruptedException {
        // Start the clock
        long start = System.currentTimeMillis();
        // Kick of multiple, asynchronous lookups
        CompletableFuture<String> result1 = asyncCompletableFuture();
        CompletableFuture<String> result2 = asyncCompletableFuture();
        CompletableFuture<String> result3 = asyncCompletableFuture();
        // Wait until they are all done
        CompletableFuture.allOf(result1, result2, result3).join();
        // Print results, including elapsed time
        LOGGER.info("Elapsed time: " + (System.currentTimeMillis() - start));
        LOGGER.info("--> " + result1.get());
        LOGGER.info("--> " + result2.get());
        LOGGER.info("--> " + result3.get());
    }

    @Async
    public CompletableFuture<String> asyncCompletableFuture() throws InterruptedException {
        // https://spring.io/guides/gs/async-method/
        LOGGER.info("Execute method asynchronously - " + Thread.currentThread().getName());
        Thread.sleep(100);
        // return new AsyncResult<>("hello world !!!!");
        return CompletableFuture.completedFuture("hello world !!!!");
    }

    @LogActionStepTracer(step = "testParallelStream")
    @Override
    public void testParallelStream() {
        Arrays.asList("Tom", "Jerry", "Mary", "Colin").parallelStream().forEach(LOGGER::info);
    }
}
